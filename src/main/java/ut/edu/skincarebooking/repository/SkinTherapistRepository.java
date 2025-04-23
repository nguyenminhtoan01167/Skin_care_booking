package ut.edu.skincarebooking.repository;

import org.springframework.stereotype.Repository;

import ut.edu.skincarebooking.model.SkinTherapist;

@Repository
public interface SkinTherapistRepository extends UserRepository<SkinTherapist> {
    // SkinTherapist-specific methods can be added here if needed

}
