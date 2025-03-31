package kidsgrowth.child_growth_tracking_system.repository;
import kidsgrowth.child_growth_tracking_system.model.Notification;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);  // Tìm thông báo theo ID người dùng
    List<Notification> findByStatus(String status);  // Tìm thông báo theo trạng thái
}
