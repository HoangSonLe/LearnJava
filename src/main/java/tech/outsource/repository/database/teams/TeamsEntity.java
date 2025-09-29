package tech.outsource.repository.database.teams;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import tech.core.common.models.Auditable;

@Data  // Lombok: sinh getter, setter, toString, equals, hashCode
@AllArgsConstructor // Lombok: sinh constructor đầy đủ tham số
@NoArgsConstructor // Lombok: sinh constructor rỗng
@EqualsAndHashCode(callSuper = false) // equals & hashCode không gọi field từ class cha
@FieldDefaults(level = AccessLevel.PRIVATE) // mặc định tất cả field là private
@Table(name = "teams") // JPA: map entity với bảng "teams"
@Entity // Đánh dấu đây là JPA entity
public class TeamsEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    Long teamId;

    @Column(name = "team_code")
    String teamCode;

    @Column(name = "team_name")
    String teamName = "";

    @Column(name = "description")
    String description;

    @Column(name = "path")
    String path;

    @Column(name = "status")
    String status;

    @Column(name = "sub_prison_code")
    String subPrisonCode;

    @Column(name = "sub_prison_name")
    String subPrisonName;

    @Column(name = "prison_code")
    String prisonCode;

    @Column(name = "prison_name")
    String prisonName;
}
