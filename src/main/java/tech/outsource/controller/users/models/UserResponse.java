package tech.outsource.controller.users.models;

public record UserResponse(
        Integer userId,
        String username,
        String email,
        Boolean deleted,
        Boolean isExpiredPwd
) {
    // Factory method tiện dụng
    public static UserResponse of(Integer userId,
                                  String username,
                                  String email,
                                  Boolean deleted,
                                  Boolean isExpiredPwd) {
        return new UserResponse(userId, username, email, deleted, isExpiredPwd);
    }
}
