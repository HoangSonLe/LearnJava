package tech.outsource.controller.users;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import tech.core.common.models.ApiErrorResponse;
import tech.core.common.models.PageImplResponse;
import tech.core.common.models.PageRequestCustom;
import tech.outsource.controller.users.models.UserResponse;
import tech.outsource.controller.users.models.UsersModelMapper;
import tech.outsource.dto.users.User;
import tech.outsource.service.users.UsersUseCaseService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
// Annotation bạn đưa ra đến từ springdoc-openapi (hoặc swagger-core) và được dùng để mô tả response cho endpoint trong tài liệu OpenAPI/Swagger.
@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
public class UsersController implements UsersAPI {
    @NonNull
    UsersUseCaseService usersUseCaseService;

    @NonNull
    UsersModelMapper usersModelMapper;

    @Override
    public PageImplResponse<UserResponse> findAll(String sorter, Integer currentPage, int pageSize) {
        PageRequestCustom pageRequest = PageRequestCustom.of(currentPage, pageSize, tech.core.common.models.SortHandleCustom.of(sorter));
        Page<User> userPage = usersUseCaseService.findAll(pageRequest);
        return new PageImplResponse<>(
                usersModelMapper.toResponse(userPage.getContent()),
                true,
                userPage.getTotalElements(),
                userPage.getSize(),
                currentPage
        );
    }

}
