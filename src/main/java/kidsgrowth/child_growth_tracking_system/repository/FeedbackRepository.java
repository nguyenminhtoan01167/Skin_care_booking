package kidsgrowth.child_growth_tracking_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kidsgrowth.child_growth_tracking_system.model.Doctor;

import kidsgrowth.child_growth_tracking_system.model.Feedback;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Doctor, Long> {
    List<Feedback> findByUserId(Long userId);  // Tìm phản hồi theo ID của người dùng
}
