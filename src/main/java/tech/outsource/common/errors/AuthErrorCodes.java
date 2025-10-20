package tech.outsource.common.errors;

import com.example.core.common.constants.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum AuthErrorCodes implements ErrorCode {
    INVALID_CREDENTIALS("AUTH_001", "Tên đăng nhập hoặc mật khẩu không đúng"),
    ACCOUNT_LOCKED("AUTH_002", "Tài khoản bị khóa"),
    TOKEN_EXPIRED("AUTH_003", "Token đã hết hạn"),
    TOKEN_INVALID("AUTH_004", "Token không hợp lệ"),
    USER_NOT_FOUND("AUTH_005", "Người dùng không tồn tại"),
    UNAUTHORIZED("AUTH_006", "Không có quyền truy cập"),
    PASSWORD_WEAK("AUTH_007", "Mật khẩu quá yếu"),
    PASSWORD_EXPIRED("AUTH_008", "Mật khẩu đã hết hạn"),
    SESSION_EXPIRED("AUTH_009", "Phiên đăng nhập đã hết hạn");
    String code;
    String message;
}
