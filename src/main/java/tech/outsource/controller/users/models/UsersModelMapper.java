package tech.outsource.controller.users.models;

import org.mapstruct.Mapper;
import tech.outsource.domain.users.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersModelMapper {

    User toDto(UserRequest userRequest);

    /**
     * Chuyển đổi từ DTO sang Response
     */
    UserResponse toResponse(User user);

    /**
     * Chuyển đổi danh sách DTO sang Response
     */
    List<UserResponse> toResponse(List<User> users);
}
