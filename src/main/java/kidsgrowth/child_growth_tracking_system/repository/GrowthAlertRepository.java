package kidsgrowth.child_growth_tracking_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kidsgrowth.child_growth_tracking_system.model.GrowthAlert;
import kidsgrowth.child_growth_tracking_system.model.GrowthRecord;
import java.util.List;
@Repository
public interface GrowthAlertRepository extends JpaRepository<GrowthAlert, Long> {
    // Tìm các cảnh báo theo GrowthRecord
    List<GrowthAlert> findByGrowthRecord(GrowthRecord growthRecord);
}
