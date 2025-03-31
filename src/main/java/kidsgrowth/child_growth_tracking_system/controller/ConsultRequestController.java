package kidsgrowth.child_growth_tracking_system.controller;

import kidsgrowth.child_growth_tracking_system.model.ConsultRequest;
import kidsgrowth.child_growth_tracking_system.service.ConsultRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consult-requests")
public class ConsultRequestController {
    @Autowired
    private ConsultRequestService consultRequestService;

    // Gửi yêu cầu tư vấn
    @PostMapping
    public ConsultRequest createConsultRequest(@RequestBody ConsultRequest consultRequest) {
        return consultRequestService.createConsultRequest(consultRequest);
    }

    // Lấy yêu cầu tư vấn theo ID
    @GetMapping("/{id}")
    public ConsultRequest getConsultRequestById(@PathVariable Long id) {
        return consultRequestService.getConsultRequestById(id);
    }

    // Lấy tất cả yêu cầu tư vấn
    @GetMapping
    public List<ConsultRequest> getAllConsultRequests() {
        return consultRequestService.getAllConsultRequests();
    }

    // Cập nhật yêu cầu tư vấn
    @PutMapping("/{id}")
    public ConsultRequest updateConsultRequest(@PathVariable Long id, @RequestBody ConsultRequest consultRequest) {
        return consultRequestService.updateConsultRequest(id, consultRequest);
    }

    // Xóa yêu cầu tư vấn
    @DeleteMapping("/{id}")
    public void deleteConsultRequest(@PathVariable Long id) {
        consultRequestService.deleteConsultRequest(id);
    }
}
