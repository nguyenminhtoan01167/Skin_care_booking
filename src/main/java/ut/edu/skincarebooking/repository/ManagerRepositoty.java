package ut.edu.skincarebooking.repository;

import org.springframework.stereotype.Repository;

import ut.edu.skincarebooking.model.Manager;

@Repository
public interface ManagerRepositoty extends UserRepository<Manager> {
    // Manager-specific methods can be added here if needed
}
