package tech.outsource.controller.auth;

import com.example.core.common.constants.CoreErrorCodes;
import com.example.core.common.exceptions.ApplicationException;
import com.example.core.common.models.ValueResponse;
import com.example.core.security.models.JwtToken;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.outsource.controller.auth.models.AuthenticationRequest;
import tech.outsource.service.auth.AuthUseCaseService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController implements AuthenticationAPI {

    @NonNull
    AuthUseCaseService authUseCaseService;

    @Override
    public ValueResponse<JwtToken> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws ApplicationException {
        try {
            JwtToken jwt = authUseCaseService.authenticate(authenticationRequest);
            return new ValueResponse<>(jwt);
        } catch (Exception e) {
            throw new ApplicationException(CoreErrorCodes.SERVER_ERROR, "Invalid credential" + e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ValueResponse<JwtToken> refreshToken(@RequestBody @Valid String refreshToken) throws ApplicationException {
        try {
            JwtToken jwtToken = authUseCaseService.refreshToken(refreshToken);
            return new ValueResponse<>(jwtToken);
        } catch (Exception e) {
            throw new ApplicationException(CoreErrorCodes.TOKEN_INVALID, "Invalid token" + e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public boolean isValidToken(@RequestBody @Valid String accessToken) {
        return authUseCaseService.checkValidToken(accessToken);
    }
}
