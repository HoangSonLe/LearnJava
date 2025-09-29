package tech.outsource.controller.teams;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.core.common.models.PageImplResponse;
import tech.outsource.controller.teams.models.TeamsResponse;

@Tag(name = "Teams Management", description = "APIs for managing teams/groups")
@RequestMapping("/v1/teams")
public interface TeamsAPI {
    @GetMapping
    PageImplResponse<TeamsResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "subPrisonCode", defaultValue = "") String subPrisonCode,
            @RequestParam(required = false, name = "sorter", defaultValue = "createdAt") String sorter,
            @RequestParam(required = false, name = "current", defaultValue = "1") Integer currentPage,
            @RequestParam(required = true, name = "pageSize") int pageSize
    );

}
