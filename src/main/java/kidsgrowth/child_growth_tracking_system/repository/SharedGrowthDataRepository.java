package kidsgrowth.child_growth_tracking_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kidsgrowth.child_growth_tracking_system.model.SharedGrowthData;
@Repository
public interface SharedGrowthDataRepository extends JpaRepository<SharedGrowthData, Long> {
    
}
