package tech.core.security.models;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record JwtToken(
        String accessToken,
        String refreshToken,
        String tokenType,
        String userId,
        String userName,
        String email
) {
    public JwtToken {
        if (tokenType == null || tokenType.isBlank()) {
            tokenType = "Bearer";
        }
    }
}