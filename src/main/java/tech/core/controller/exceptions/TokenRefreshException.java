package tech.core.controller.exceptions;

import org.springframework.http.HttpStatus;
import tech.core.common.constants.CoreErrorCodes;

public class TokenRefreshException extends ApplicationException {
    public TokenRefreshException() {
        super(CoreErrorCodes.TOKEN_INVALID, CoreErrorCodes.TOKEN_INVALID.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
