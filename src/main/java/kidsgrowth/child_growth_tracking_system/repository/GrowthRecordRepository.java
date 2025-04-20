package kidsgrowth.child_growth_tracking_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kidsgrowth.child_growth_tracking_system.model.GrowthRecord;

import java.util.Date;
import java.util.List;
@Repository
public interface GrowthRecordRepository extends JpaRepository<GrowthRecord, Long> {

    // Tìm các chỉ số tăng trưởng theo ID của trẻ
    List<GrowthRecord> findByChildId(Long childId);  

    // Tìm các chỉ số tăng trưởng trong khoảng thời gian và theo ID trẻ
    List<GrowthRecord> findByChildIdAndRecordDateBetween(Long childId, Date startDate, Date endDate); 
}
