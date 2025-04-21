package kidsgrowth.child_growth_tracking_system.repository;

import kidsgrowth.child_growth_tracking_system.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}