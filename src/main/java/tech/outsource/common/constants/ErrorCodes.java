package tech.outsource.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import tech.core.common.constants.ErrorCode;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCodes implements ErrorCode {
    DUPLICATE_DATA_ERROR("DUPLICATE_DATA_ERROR", "Dữ liệu trùng lặp"),;

    String code;
    String message;
}
