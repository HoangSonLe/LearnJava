package tech.outsource.service.teams;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import tech.core.common.models.PageRequestCustom;
import tech.outsource.dto.teams.Teams;
import tech.outsource.dto.teams.TeamsMapper;
import tech.outsource.dto.teams.TeamsSearchCriteria;
import tech.outsource.repository.database.teams.TeamsEntity;
import tech.outsource.repository.database.teams.TeamsRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TeamsQueryService {
    TeamsRepository teamsRepository;

    TeamsMapper teamsMapper;

    public Page<Teams> findAll(TeamsSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        Page<TeamsEntity> teamsPage = teamsRepository.findAll(searchCriteria, pageRequestCustom.pageRequest());
        return teamsPage.map(teamsMapper::toDto);
    }
}
