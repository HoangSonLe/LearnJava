package tech.outsource.repository.database.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformationEntity, Integer> {
}
