package kidsgrowth.child_growth_tracking_system.repository;
import kidsgrowth.child_growth_tracking_system.model.SessionLog;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SessionLogRepository extends JpaRepository<SessionLog, Long> {
    
}
