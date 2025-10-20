package tech.outsource.controller.teams;

import com.example.core.common.models.ApiErrorResponse;
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
import tech.outsource.controller.teams.models.TeamsModelMapper;
import tech.outsource.controller.teams.models.TeamsRequest;
import tech.outsource.dto.teams.Teams;
import tech.outsource.service.teams.TeamsUseCaseService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
public class TeamController implements TeamAPI {

    @NonNull
    TeamsModelMapper teamsModelMapper;

    @NonNull
    TeamsUseCaseService teamsUseCaseService;
    @Override
    public void create(@RequestBody @Valid TeamsRequest teamsRequest){
        Teams team = teamsModelMapper.toDto(teamsRequest);
        teamsUseCaseService.create(team);
    }
}
