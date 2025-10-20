package tech.outsource.domain.users;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record UserInformation(
        Integer userInformationId,
        Integer userId,
        String email,
        String firstName,
        String lastName,
        String gender,
        String avatar,
        String phone,
        Boolean isExpiredPwd,
        LocalDate birthday,
        LocalDateTime createdAt
) {}

