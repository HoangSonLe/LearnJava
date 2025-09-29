package tech.outsource.repository.database.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import tech.core.common.models.Auditable;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = PRIVATE)
@Builder
public class UserEntity extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    Integer userId;
    @Column(nullable = false, unique = true, name = "username")
    String username;
    @Column(nullable = false, name = "password")
    String password;
    @Column(nullable = false, unique = true, name = "email")
    String email;
    @Builder.Default
    @Column(nullable = false, name = "deleted")
    Boolean deleted = false;
    @Column(name = "isExpiredPwd", nullable = false)
    private Boolean isExpiredPwd = true;
}
