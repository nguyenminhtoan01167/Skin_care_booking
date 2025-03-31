package kidsgrowth.child_growth_tracking_system.repository;

import org.springframework.stereotype.Repository;

import kidsgrowth.child_growth_tracking_system.model.HealthReport;
@Repository
public interface HealthReportRepository extends JpaRepository<HealthReport, Long> {
    
}
