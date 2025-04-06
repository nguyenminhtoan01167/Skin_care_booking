package kidsgrowth.child_growth_tracking_system.repository;
import org.springframework.stereotype.Repository;

import kidsgrowth.child_growth_tracking_system.model.DashboardStats;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DashboardStatsRepository extends JpaRepository<DashboardStats, Long> {
    
}
