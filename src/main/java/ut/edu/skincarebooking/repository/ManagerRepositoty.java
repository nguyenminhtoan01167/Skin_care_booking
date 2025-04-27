package ut.edu.skincarebooking.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.skincarebooking.model.Manager;

@Repository
public interface ManagerRepositoty extends JpaRepository<Manager, UUID> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    // Find a customer by email
    Optional<Manager> findByEmail(String email);
    // Manager-specific methods can be added here if needed
}
