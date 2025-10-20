package tech.outsource.repository.database.users;

import com.example.core.common.models.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_information")
@Data
public class UserInformationEntity  extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_information_id")
    private Integer userInformationId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "email", length = 150, unique = true)
    private String email;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "is_expired_pwd")
    private Boolean isExpiredPwd;

    @Column(name = "birthday")
    private LocalDate birthday;
}

