package tech.core.controller.exceptions;

import org.springframework.http.HttpStatus;
import tech.core.common.constants.CoreErrorCodes;

public class ConflictsException extends ApplicationException {
    public ConflictsException() {
        super(CoreErrorCodes.CONFLICT, CoreErrorCodes.CONFLICT.getMessage(), HttpStatus.CONFLICT);
    }
}