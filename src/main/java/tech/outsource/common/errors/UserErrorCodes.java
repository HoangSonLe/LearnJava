package tech.outsource.common.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import tech.core.common.constants.ErrorCode;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum UserErrorCodes implements ErrorCode {
    NOT_FOUND("NOT_FOUND", "Không tìm thấy dữ liệu người dùng"),
    DUPLICATE_DATA_ERROR("DUPLICATE_DATA_ERROR", "Dữ liệu đã tồn tại")
    ;
    String code;
    String message;
}
