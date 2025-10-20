package tech.outsource.controller.users;

import com.example.core.common.models.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.outsource.controller.users.models.UserResponse;

@Tag(name = "Teams Management", description = "APIs for managing teams/groups")
@RequestMapping("/v1/users")
public interface UsersAPI {
    @GetMapping
    PageResponse<UserResponse> findAll(
            @RequestParam(required = false, name = "sorter", defaultValue = "createdAt") String sorter,
            @RequestParam(required = false, name = "current", defaultValue = "1") Integer currentPage,
            @RequestParam(required = true, name = "pageSize") int pageSize
    );

}
