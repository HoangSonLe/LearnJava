package tech.outsource.repository.database.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsernameAndEmail(String username, String email);

    Optional<UserEntity> findByUsername(String username);
}
