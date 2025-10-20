package tech.outsource.domain.teams;


public record Teams(Long teamId, String teamCode, String teamName, String description, String path, String status

        , String subPrisonCode, String subPrisonName, String prisonCode, String prisonName) {

    public static Teams of(String teamCode, String teamName, String description, String path, String status,
                           String subPrisonCode, String subPrisonName, String prisonCode, String prisonName) {
        return new Teams(
                null,
                teamCode,
                teamName,
                description,
                path,
                status,
                subPrisonCode,
                subPrisonName,
                prisonCode,
                prisonName
        );
    }

    /**
     * Kiểm tra team có đang hoạt động không
     */
    public boolean isActive() {
        return "ACTIVE".equals(status);
    }

    /**
     * Kiểm tra team có bị xóa không
     */
    public boolean isDeleted() {
        return "DELETED".equals(status);
    }
}
