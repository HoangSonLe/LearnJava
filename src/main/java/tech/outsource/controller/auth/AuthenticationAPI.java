package tech.outsource.controller.auth;

import com.example.core.common.exceptions.ApplicationException;
import com.example.core.common.models.ValueResponse;
import com.example.core.security.models.JwtToken;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.outsource.controller.auth.models.AuthenticationRequest;

@RequestMapping("/v1/auth")
public interface AuthenticationAPI {

    @PostMapping("/login")
    public ValueResponse<JwtToken> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws ApplicationException;

    @PostMapping("/refresh-token")
    public ValueResponse<JwtToken> refreshToken(@Valid @RequestBody String refreshToken) throws ApplicationException;

    @PostMapping("/validate")
    public boolean isValidToken(@RequestBody @Valid String accessToken);
}
