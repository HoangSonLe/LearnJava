package tech.outsource.controller.auth.models;

import jakarta.validation.constraints.*;

public record AuthenticationRequest(

        @NotBlank(message = "Tên đăng nhập không được để trống")
        @Size(max = 100, message = "Tên đăng nhập không được vượt quá 100 ký tự")
        String username,

        @NotBlank(message = "Mật khẩu không được để trống")
        @Size(min = 6, max = 100, message = "Mật khẩu phải từ 6–100 ký tự")
        String password
) {
}
