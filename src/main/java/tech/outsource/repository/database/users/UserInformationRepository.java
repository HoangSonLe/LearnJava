package tech.outsource.repository.database.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInformationRepository extends JpaRepository<UserInformationEntity, Integer> {
}
