package kidsgrowth.child_growth_tracking_system.repository;
import kidsgrowth.child_growth_tracking_system.model.Payment;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Tìm thanh toán theo ID người dùng
    List<Payment> findByUserId(Long userId);

    // Tìm thanh toán theo trạng thái thanh toán
    List<Payment> findByPaymentStatus(String paymentStatus);  // Đổi 'status' thành 'paymentStatus'
}