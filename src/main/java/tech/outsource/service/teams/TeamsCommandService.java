package tech.outsource.service.teams;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.core.common.constants.ErrorCode;
import tech.core.controller.exceptions.ApplicationException;
import tech.outsource.common.constants.ETeamsStatus;
import tech.outsource.common.constants.ErrorCodes;
import tech.outsource.dto.teams.Teams;
import tech.outsource.dto.teams.TeamsMapper;
import tech.outsource.repository.database.teams.TeamsEntity;
import tech.outsource.repository.database.teams.TeamsRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TeamsCommandService {
    @NonNull
    TeamsRepository teamsRepository;

    @NonNull
    TeamsMapper teamsMapper;

    @Transactional
    public Teams create(Teams teams) {
        // Kiểm tra team code đã tồn tại chưa
        if (teamsRepository.existsByTeamCodeAndStatusNot(teams.teamCode(), ETeamsStatus.DELETED.getCode())) {
            throw new ApplicationException(
                    ErrorCodes.DUPLICATE_DATA_ERROR,
                    "Mã đội đã tồn tại: " + teams.teamCode(),
                    HttpStatus.CONFLICT
            );
        }

        TeamsEntity teamsEntity = teamsMapper.toEntity(teams);
        teamsEntity.setStatus("ACTIVE");
        TeamsEntity savedEntity = teamsRepository.save(teamsEntity);
        return teamsMapper.toDto(savedEntity);
    }
}
