package tech.outsource.domain.teams;

import com.example.core.utils.StringHelper;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import tech.outsource.repository.database.teams.TeamsEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
public record TeamsSearchCriteria(String search, String subPrisonCode) {
    public static TeamsSearchCriteria of(String search, String subPrisonCode) {
        return TeamsSearchCriteria.builder()
                .search(search)
                .subPrisonCode(subPrisonCode)
                .build();
    }
    public List<Specification<TeamsEntity>> specifications() {

        List<Specification<TeamsEntity>> specs = new ArrayList<>();
        if(StringHelper.hasText(search)){
            String pattern = "%" + lowerSearch() + "%";
            specs.add(((root, query, criteriaBuilder) -> criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("teamCode")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("teamName")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("subPrisonCode")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("subPrisonName")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("prisonCode")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("prisonName")), pattern)
            )));        }
        if(!hasSubPrisonCodeEmpty()){
            specs.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("subPrisonCode"), subPrisonCode));
        }
        return specs;
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
