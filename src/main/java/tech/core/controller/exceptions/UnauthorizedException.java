package tech.core.controller.exceptions;


import org.springframework.http.HttpStatus;
import tech.core.common.constants.CoreErrorCodes;

public class UnauthorizedException extends ApplicationException {
    public UnauthorizedException() {
        super(CoreErrorCodes.SYSTEM_AUTHORIZATION, CoreErrorCodes.SYSTEM_AUTHORIZATION.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}