package tech.outsource.dto.teams;

import lombok.Builder;

import java.util.Objects;

@Builder
public record TeamsSearchCriteria(String search, String subPrisonCode) {
    public static TeamsSearchCriteria of(String search, String subPrisonCode) {
        return TeamsSearchCriteria.builder()
                .search(search)
                .subPrisonCode(subPrisonCode)
                .build();
    }

    public boolean hasSearchEmpty() {
        return Objects.isNull(search) || search.isEmpty();
    }
    public boolean hasSubPrisonCodeEmpty() {
        return Objects.isNull(subPrisonCode) || subPrisonCode.isEmpty();
    }
    public String lowerSearch() {
        return search.toLowerCase();
    }
}
