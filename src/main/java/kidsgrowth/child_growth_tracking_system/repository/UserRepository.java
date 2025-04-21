package kidsgrowth.child_growth_tracking_system.repository;

import kidsgrowth.child_growth_tracking_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findById(Long id);
    
    // Tìm kiếm người dùng theo ID
    Optional<User> findById(Integer id);
    
    // Tìm kiếm người dùng theo email
    Optional<User> findByEmail(String email);
    
    // Kiểm tra xem email đã tồn tại hay chưa
    boolean existsByEmail(String email);
    
    // Lấy danh sách người dùng theo vai trò
    List<User> findByRole(String role);
    
    // Xóa người dùng theo ID
    void deleteById(Integer id);
}
