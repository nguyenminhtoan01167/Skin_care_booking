package ut.edu.skincarebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.skincarebooking.model.SkinTherapist;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface SkinTherapistRepository extends JpaRepository<SkinTherapist, UUID> {

    // Kiểm tra email đã tồn tại
    boolean existsByEmail(String email);

    // Kiểm tra username đã tồn tại
    boolean existsByUsername(String username);

    // Tìm SkinTherapist theo email
    Optional<SkinTherapist> findByEmail(String email);

    // Tìm SkinTherapist theo username (nếu cần)
    Optional<SkinTherapist> findByUsername(String username);
}
