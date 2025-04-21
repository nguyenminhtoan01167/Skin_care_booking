package kidsgrowth.child_growth_tracking_system.repository;

import kidsgrowth.child_growth_tracking_system.model.PregnancyGrowthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PregnancyGrowthRecordRepository extends JpaRepository<PregnancyGrowthRecord, Long> {
}