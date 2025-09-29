package tech.core.controller.exceptions;

import org.springframework.http.HttpStatus;
import tech.core.common.constants.CoreErrorCodes;

public class ForbiddenException extends ApplicationException {
    public ForbiddenException() {
        super(CoreErrorCodes.FORBIDDEN, CoreErrorCodes.FORBIDDEN.getMessage(), HttpStatus.FORBIDDEN);
    }
}
