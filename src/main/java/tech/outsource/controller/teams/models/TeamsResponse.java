package tech.outsource.controller.teams.models;

public record TeamsResponse(Long teamId,
                            String teamCode,
                            String teamName,
                            String description,
                            String path,
                            String subPrisonCode,
                            String subPrisonName) {
    public static TeamsResponse of(Long teamId, String teamCode, String teamName,
                                   String description, String path, String subPrisonCode, String subPrisonName) {
        return new TeamsResponse(
                teamId, teamCode, teamName, description, path, subPrisonCode, subPrisonName
        );
    }
}
