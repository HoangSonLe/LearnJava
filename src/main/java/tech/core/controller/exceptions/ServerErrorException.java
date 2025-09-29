package tech.core.controller.exceptions;

import org.springframework.http.HttpStatus;
import tech.core.common.constants.CoreErrorCodes;

public class ServerErrorException extends ApplicationException {
    public ServerErrorException() {
        super(CoreErrorCodes.SERVER_ERROR, CoreErrorCodes.SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
