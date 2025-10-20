package tech.outsource.controller.teams.models;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.outsource.domain.teams.Teams;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamsModelMapper {

    @Mapping(target = "teamId", ignore = true)
    Teams toDto(TeamsRequest teamsRequest);

    /**
     * Chuyển đổi từ DTO sang Response
     */
    TeamsResponse toResponse(Teams teams);

    /**
     * Chuyển đổi danh sách DTO sang Response
     */
    List<TeamsResponse> toResponse(java.util.List<Teams> teams);
}
