package ut.edu.skincarebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.skincarebooking.model.Staff;
import java.util.Optional;
import java.util.UUID;



@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID> {
    boolean existsByEmail(String email); // Kiểm tra email đã tồn tại
    boolean existsByUsername(String username); // Kiểm tra username đã tồn tại
    Optional<Staff> findByEmail(String email); // Tìm Staff theo email
}
