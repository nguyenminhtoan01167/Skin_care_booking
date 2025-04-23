package ut.edu.skincarebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ut.edu.skincarebooking.model.User;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<T> findByUsername(String username);
    Optional<T> findByEmail(String email);
    boolean existsByUsername(String username);
}
