package tech.outsource.domain.users;

public record User(
        Integer userId,
        String email,
        String password,
        String username
) {
}