package ut.edu.skincarebooking.repository;

import org.springframework.stereotype.Repository;

import ut.edu.skincarebooking.model.Customer;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {
    // Customer-specific methods can be added here if needed
}
