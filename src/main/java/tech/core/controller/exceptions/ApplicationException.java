package tech.core.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import tech.core.common.constants.ErrorCode;

public class ApplicationException extends RuntimeException {
    final ErrorCode errorCode;
    final String message;
    final HttpStatus httpStatus;

    public String asMessage() {
        return StringUtils.hasText(this.message) ? this.message : this.errorCode.getMessage();
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public ApplicationException(final ErrorCode errorCode, final String message, final HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
