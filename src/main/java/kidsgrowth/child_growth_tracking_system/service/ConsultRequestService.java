package kidsgrowth.child_growth_tracking_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kidsgrowth.child_growth_tracking_system.repository.ConsultRequestRepository;
import kidsgrowth.child_growth_tracking_system.model.ConsultRequest;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultRequestService {
    @Autowired
    private ConsultRequestRepository consultRequestRepository;

    // Tạo mới yêu cầu tư vấn
    public ConsultRequest createConsultRequest(ConsultRequest consultRequest) {
        return consultRequestRepository.save(consultRequest);
    }

    // Lấy thông tin yêu cầu tư vấn theo ID
    public ConsultRequest getConsultRequestById(Long id) {
        Optional<ConsultRequest> consultRequest = consultRequestRepository.findById(id);
        return consultRequest.orElse(null);
    }

    // Lấy tất cả yêu cầu tư vấn
    public List<ConsultRequest> getAllConsultRequests() {
        return consultRequestRepository.findAll();
    }

    // Cập nhật yêu cầu tư vấn
    public ConsultRequest updateConsultRequest(Long id, ConsultRequest consultRequest) {
        Optional<ConsultRequest> existingConsultRequest = consultRequestRepository.findById(id);
        if (existingConsultRequest.isPresent()) {
            consultRequest.setId(id);
            return consultRequestRepository.save(consultRequest);
        }
        return null;
    }

    // Xóa yêu cầu tư vấn
    public void deleteConsultRequest(Long id) {
        consultRequestRepository.deleteById(id);
    }
}
