package tech.outsource.controller.auth.models;

import org.mapstruct.Mapper;
import tech.outsource.controller.users.models.UserRequest;
import tech.outsource.controller.users.models.UserResponse;
import tech.outsource.dto.users.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthenticationModelMapper {

}
