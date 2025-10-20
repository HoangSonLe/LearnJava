package tech.outsource.common.constants;

import com.example.core.common.constants.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCodes implements ErrorCode {
    DUPLICATE_DATA_ERROR("DUPLICATE_DATA_ERROR", "Dữ liệu trùng lặp"),
    INVALID_INPUT("INVALID_INPUT", "Dữ liệu không hợp lệ"),;

    String code;
    String message;
}
