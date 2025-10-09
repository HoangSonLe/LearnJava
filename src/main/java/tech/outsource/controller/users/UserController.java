package tech.outsource.controller.users;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.core.common.models.ApiErrorResponse;
import tech.outsource.controller.users.models.UsersModelMapper;
import tech.outsource.controller.users.models.UserRequest;
import tech.outsource.dto.users.User;
import tech.outsource.service.users.UsersUseCaseService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
public class UserController implements UserAPI {

    @NonNull
    UsersModelMapper usersModelMapper;

    @NonNull
    UsersUseCaseService usersUseCaseService;

    @Override
    public void create(@RequestBody @Valid UserRequest userRequest) {
        User user = usersModelMapper.toDto(userRequest);
        usersUseCaseService.create(user);
    }
}
