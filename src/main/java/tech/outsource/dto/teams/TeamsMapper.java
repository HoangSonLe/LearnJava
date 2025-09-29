package tech.outsource.dto.teams;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tech.outsource.repository.database.teams.TeamsEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamsMapper {
    Teams toDto(TeamsEntity teamsEntity);

    TeamsEntity toEntity(Teams teams);

    List<Teams> toDto(List<TeamsEntity> teamsEntities);

    List<TeamsEntity> toEntity(List<Teams> teams);
}
