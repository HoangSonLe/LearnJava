package tech.outsource.controller.teams;

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
import tech.outsource.controller.teams.models.TeamsModelMapper;
import tech.outsource.controller.teams.models.TeamsResponse;
import tech.outsource.domain.teams.Teams;
import tech.outsource.domain.teams.TeamsSearchCriteria;
import tech.outsource.service.teams.TeamsUseCaseService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
// Annotation bạn đưa ra đến từ springdoc-openapi (hoặc swagger-core) và được dùng để mô tả response cho endpoint trong tài liệu OpenAPI/Swagger.
@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
public class TeamsController implements TeamsAPI {
    @NonNull
    TeamsUseCaseService teamsUseCaseService;

    @NonNull
    TeamsModelMapper teamsModelMapper;

    @Override
    public PageResponse<TeamsResponse> findAll(String search, String subPrisonCode, String sorter, Integer currentPage, int pageSize) {
        TeamsSearchCriteria searchCriteria = TeamsSearchCriteria.of(search, subPrisonCode);
        PageRequestCustom pageRequest = PageRequestCustom.of(currentPage, pageSize, SortHandleCustom.from(sorter));
        // Gọi async service
        CompletableFuture<Page<Teams>> future = teamsUseCaseService.findAllAsync(searchCriteria, pageRequest);

        // Đợi kết quả (block tại đây)
        Page<Teams> teamsPage = future.join();
        return new PageResponse<>(
                teamsModelMapper.toResponse(teamsPage.getContent()),
                true,
                teamsPage.getTotalElements(),
                teamsPage.getSize(),
                currentPage
        );
    }

}
