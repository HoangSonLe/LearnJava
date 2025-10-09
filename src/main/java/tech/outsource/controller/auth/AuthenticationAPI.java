package tech.outsource.controller.auth;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.core.common.models.ValueResponse;
import tech.core.security.models.JwtToken;
import tech.outsource.controller.auth.models.AuthenticationRequest;

@RequestMapping("/v1/auth")
public interface AuthenticationAPI {

    @PostMapping("/login")
    public ValueResponse<JwtToken> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception;

    @PostMapping("/refresh-token")
    public ValueResponse<JwtToken> refreshToken(@Valid @RequestBody String refreshToken) throws Exception;

    @PostMapping("/validate")
    public boolean isValidToken(@RequestBody @Valid String accessToken);
}
