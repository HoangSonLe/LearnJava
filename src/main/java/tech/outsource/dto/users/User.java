package tech.outsource.dto.users;

public record User(
        Integer userId,
        String email,
        String password,
        String username
) {
}