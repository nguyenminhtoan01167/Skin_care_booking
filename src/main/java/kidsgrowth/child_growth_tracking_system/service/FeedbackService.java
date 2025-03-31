package kidsgrowth.child_growth_tracking_system.service;
import kidsgrowth.child_growth_tracking_system.model.Feedback;
import kidsgrowth.child_growth_tracking_system.repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    // Tạo mới phản hồi
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Lấy thông tin phản hồi theo ID
    public Feedback getFeedbackById(Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        return feedback.orElse(null);
    }

    // Lấy tất cả phản hồi
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    // Cập nhật phản hồi
    public Feedback updateFeedback(Long id, Feedback feedback) {
        Optional<Feedback> existingFeedback = feedbackRepository.findById(id);
        if (existingFeedback.isPresent()) {
            feedback.setId(id);
            return feedbackRepository.save(feedback);
        }
        return null;
    }

    // Xóa phản hồi
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
