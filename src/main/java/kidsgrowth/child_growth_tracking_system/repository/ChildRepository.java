package kidsgrowth.child_growth_tracking_system.repository;

import kidsgrowth.child_growth_tracking_system.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ChildRepository extends JpaRepository<Child, Long>  {
    List<Child> findByUserId(Long userId);  // Tìm trẻ em theo ID của người dùng
}

