package tech.outsource.controller.users;

import com.example.core.common.models.ApiErrorResponse;
import com.example.core.common.models.PageRequestCustom;
import com.example.core.common.models.PageResponse;
import com.example.core.common.models.SortHandleCustom;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import tech.outsource.controller.users.models.UserResponse;
import tech.outsource.controller.users.models.UsersModelMapper;
import tech.outsource.domain.users.User;
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
    public PageResponse<UserResponse> findAll(String sorter, Integer currentPage, int pageSize) {
        PageRequestCustom pageRequest = PageRequestCustom.of(currentPage, pageSize, SortHandleCustom.from(sorter));
        Page<User> userPage = usersUseCaseService.findAll(pageRequest);
        return new PageResponse<>(
                usersModelMapper.toResponse(userPage.getContent()),
                true,
                userPage.getTotalElements(),
                userPage.getSize(),
                currentPage
        );
    }

}
