package tech.outsource.repository.database.teams;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import tech.outsource.domain.teams.TeamsSearchCriteria;

import java.util.Optional;

public interface TeamsRepository extends JpaRepository<TeamsEntity, Long>, JpaSpecificationExecutor<TeamsEntity> {

    Optional<TeamsEntity> findByTeamCode(String teamCode);

    // Example of custom query with search criteria
    @Query("""
                SELECT t FROM TeamsEntity t
                WHERE
                        t.status = 'ACTIVE'
                    AND (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                            OR LOWER(t.teamCode) LIKE %:#{#searchCriteria.lowerSearch()}%
                            OR LOWER(t.teamName) LIKE %:#{#searchCriteria.lowerSearch()}%)
                    AND (:#{#searchCriteria.hasSubPrisonCodeEmpty()} = TRUE
                            OR t.subPrisonCode LIKE %:#{#searchCriteria.subPrisonCode()}%)
                ORDER BY t.teamCode ASC
            """)
    Page<TeamsEntity> customQueryFindAll(TeamsSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
                SELECT t FROM TeamsEntity t
                WHERE
                        t.status = 'ACTIVE'
                    AND t.subPrisonCode = :subPrisonCode
                ORDER BY t.teamCode ASC
            """)
    Page<TeamsEntity> findBySubPrisonCode(String subPrisonCode, Pageable pageable);

    /**
     * Kiểm tra team code đã tồn tại chưa
     */
    boolean existsByTeamCodeAndStatusNot(String teamCode, String status);

}

