package tech.outsource.service.auth;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.core.cache.IJwtCache;
import tech.core.controller.exceptions.ApplicationException;
import tech.core.controller.exceptions.TokenRefreshException;
import tech.core.encrypt.AesAlgorithm;
import tech.core.security.models.JwtProperties;
import tech.core.security.models.JwtToken;
import tech.core.security.service.JWTTokenService;
import tech.core.utils.JsonHelper;
import tech.outsource.common.errors.AuthErrorCodes;
import tech.outsource.controller.auth.models.AuthenticationRequest;
import tech.outsource.dto.users.User;
import tech.outsource.dto.users.UserId;
import tech.outsource.dto.users.UserInformation;
import tech.outsource.service.users.UsersQueryService;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthUseCaseService {
    @NonNull
    UsersQueryService usersQueryService;

    @NonNull
    JWTTokenService jwtTokenService;

    @NonNull
    JwtProperties jwtProperties;

    IJwtCache jwtCache;

    @NonNull
    PasswordEncoder passwordEncoder;

    @Autowired
    public AuthUseCaseService(
            UsersQueryService usersQueryService,
            JWTTokenService jwtTokenService,
            JwtProperties jwtProperties,
            @Qualifier("inMemoryJwtCache") IJwtCache jwtCache, @NonNull PasswordEncoder passwordEncoder
    ) {
        this.usersQueryService = usersQueryService;
        this.jwtTokenService = jwtTokenService;
        this.jwtProperties = jwtProperties;
        this.jwtCache = jwtCache;
        this.passwordEncoder = passwordEncoder;
    }

    public void cacheToken(JwtToken jwtToken) {
        long refreshTtl = jwtProperties.getJwtAccessTokenExpirationS();

        jwtCache.put(jwtToken.refreshToken(), jwtToken, refreshTtl);
    }

    public User getUserFromToken(String token) {
        try {
            Jwt jwt = jwtTokenService.getJwt(token);
            Integer userId = Optional.ofNullable(jwt.getSubject())
                    .map(Integer::valueOf)
                    .orElseThrow(() -> new ApplicationException(
                            AuthErrorCodes.TOKEN_INVALID,
                            AuthErrorCodes.TOKEN_INVALID.getMessage(),
                            HttpStatus.UNAUTHORIZED
                    ));
            return usersQueryService.findById(userId);

        }
        catch (Exception e) {
            throw  new TokenRefreshException();
        }
    }

    public boolean checkValidToken(String accessToken){
        try {
//            Spring Security thiết kế vậy để bảo mật:
//            Token hết hạn → ném JwtException
//            Token sai chữ ký → ném BadJwtException
//            Token sai format → ném JwtValidationException
            Jwt jwt = jwtTokenService.getJwt(accessToken);
            Instant expiresAt = jwt.getExpiresAt();
            return expiresAt != null && expiresAt.isAfter(Instant.now());
        } catch (JwtValidationException e) {
            log.warn("Invalid JWT format: {}", e.getMessage());
        } catch (JwtException e) {
            log.warn("JWT validation failed: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error validating token", e);
        }
        return false;
    }

    public JwtToken createToken(UserId userId) throws Exception {
        UserInformation userInformation = usersQueryService.findByIdToUserInformation(userId);

        Map<String, Object> claims = new HashMap<>();
        claims.put(User.class.getName(),
                AesAlgorithm.encrypt(JsonHelper.toJson(userInformation))
        );

        String accessToken = jwtTokenService.createAccessToken(userInformation.userId().toString(), claims);
        String refreshToken = jwtTokenService.createRefreshToken(userInformation.userId().toString(), claims);
        String encryptUserId = AesAlgorithm.encrypt(JsonHelper.toJson(userInformation.userId()));

        JwtToken jwtToken = JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(encryptUserId)
                .email(userInformation.email())
                .build();

        cacheToken(jwtToken);
        return jwtToken;
    }

    public JwtToken refreshToken(String refreshToken) throws Exception {
        Jwt jwt = jwtTokenService.getJwt(refreshToken);

        if (jwt.getExpiresAt() == null || jwt.getExpiresAt().isBefore(Instant.now())) {
            throw new TokenRefreshException();
        }

        Integer userId = Integer.valueOf(jwt.getSubject());
        return createToken(new UserId(userId));
    }

    public JwtToken authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        User user = usersQueryService.findByUsername(authenticationRequest.username());

        if (!passwordEncoder.matches(authenticationRequest.password(), user.password())) {
            throw new ApplicationException(
                    AuthErrorCodes.INVALID_CREDENTIALS,
                    AuthErrorCodes.INVALID_CREDENTIALS.getMessage(),
                    HttpStatus.UNAUTHORIZED
            );
        }
        return createToken(new UserId(user.userId()));
    }
}
