package tech.outsource.domain.teams;


import com.example.core.common.interfaces.IMapper;
import org.mapstruct.Mapper;
import tech.outsource.repository.database.teams.TeamsEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamsMapper extends IMapper<TeamsEntity, Teams> {

    List<Teams> toDto(List<TeamsEntity> teamsEntities);

    List<TeamsEntity> toEntity(List<Teams> teams);
}
