package kidsgrowth.child_growth_tracking_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kidsgrowth.child_growth_tracking_system.model.GrowthAlert;
import java.util.List;
@Repository
public interface GrowthAlertRepository extends JpaRepository<GrowthAlert, Long> {
    List<GrowthAlert> findByGrowthRecordId(Long growthRecordId);  // Tìm cảnh báo theo ID của chỉ số tăng trưởng
}
