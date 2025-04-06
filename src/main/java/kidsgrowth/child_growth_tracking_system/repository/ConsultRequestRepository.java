package kidsgrowth.child_growth_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kidsgrowth.child_growth_tracking_system.model.ConsultRequest;

import java.util.List;
@Repository

public interface ConsultRequestRepository extends JpaRepository<ConsultRequest, Long> {
    List<ConsultRequest> findByUserId(Long userId);  // Tìm yêu cầu tư vấn theo ID của người dùng
    List<ConsultRequest> findByStatus(String status);  // Tìm yêu cầu tư vấn theo trạng thái
    List<ConsultRequest> findByDoctorId(Long doctorId);  // Tìm yêu cầu tư vấn theo bác sĩ
}
