package tech.core.controller.exceptions;

import org.springframework.http.HttpStatus;
import tech.core.common.constants.CoreErrorCodes;

public class RequestInvalidException extends ApplicationException {
    public RequestInvalidException() {
        super(CoreErrorCodes.REQUEST_INVALID, "RequestInvalidException", HttpStatus.BAD_REQUEST);
    }
}