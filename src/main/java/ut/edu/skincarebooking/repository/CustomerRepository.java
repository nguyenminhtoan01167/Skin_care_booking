package ut.edu.skincarebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.skincarebooking.model.Customer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    // Check if a customer exists by email
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    // Find a customer by email
    Optional<Customer> findByEmail(String email);
}
