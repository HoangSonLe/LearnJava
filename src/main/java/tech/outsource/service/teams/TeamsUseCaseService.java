package tech.outsource.service.teams;

import com.example.core.common.models.PageRequestCustom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import tech.outsource.domain.teams.Teams;
import tech.outsource.domain.teams.TeamsSearchCriteria;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TeamsUseCaseService {

    @NonNull
    TeamsCommandService teamsCommandService;

    @NonNull
    TeamsQueryService teamsQueryService;

    public Page<Teams> findAll(TeamsSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return teamsQueryService.findAll(searchCriteria, pageRequestCustom);
    }

    public void create(Teams teams) {
        teamsCommandService.create(teams);
    }
}
