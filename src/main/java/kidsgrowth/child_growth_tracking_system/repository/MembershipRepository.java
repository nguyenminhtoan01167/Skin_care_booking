package kidsgrowth.child_growth_tracking_system.repository;
import kidsgrowth.child_growth_tracking_system.model.Membership;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MembershipRepository extends JpaRepository<UserMembership, Long> {
    List<UserMembership> findByUserId(Long userId);  // Tìm lịch sử gói thành viên theo ID người dùng
    
}
