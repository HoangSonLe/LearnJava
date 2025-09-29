package tech.outsource.service.teams;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import tech.core.common.models.PageRequestCustom;
import tech.outsource.dto.teams.Teams;
import tech.outsource.dto.teams.TeamsSearchCriteria;

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
