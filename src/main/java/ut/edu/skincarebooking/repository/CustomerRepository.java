package ut.edu.skincarebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.skincarebooking.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Check if a customer exists by email
    boolean existsByEmail(String email);

    // Find a customer by email
    Optional<Customer> findByEmail(String email);
}
