package kidsgrowth.child_growth_tracking_system.model;
import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Liên kết với bảng User (người nhận thông báo)

    private String message;  // Nội dung thông báo
    private String notificationType;  // Loại thông báo (alert, reminder, update)
    private String status;  // Trạng thái của thông báo (sent, pending, failed)
    private Date createdAt;  // Thời gian tạo thông báo
    private Date sentAt;  // Thời gian gửi thông báo (nếu đã gửi)

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
