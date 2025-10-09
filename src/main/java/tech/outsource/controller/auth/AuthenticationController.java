package tech.outsource.controller.auth;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.core.common.models.ValueResponse;
import tech.core.security.models.JwtToken;
import tech.outsource.controller.auth.models.AuthenticationRequest;
import tech.outsource.service.auth.AuthUseCaseService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController implements AuthenticationAPI {

    @NonNull
    AuthUseCaseService authUseCaseService;

    @Override
    public ValueResponse<JwtToken> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception {
        JwtToken jwt = authUseCaseService.authenticate(authenticationRequest);
        return  new ValueResponse<JwtToken>(jwt);
    }

    @Override
    public ValueResponse<JwtToken> refreshToken(@RequestBody @Valid String refreshToken) throws Exception {
        JwtToken jwtToken = authUseCaseService.refreshToken(refreshToken);
        return new ValueResponse<>(jwtToken);
    }

    @Override
    public boolean isValidToken(@RequestBody @Valid String accessToken) {
        return authUseCaseService.checkValidToken(accessToken);
    }
}
