package kidsgrowth.child_growth_tracking_system.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class SharedGrowthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;  // Liên kết với bảng Child (trẻ em)

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;  // Liên kết với bảng User (bác sĩ)

    private Date sharedAt;  // Thời gian chia sẻ dữ liệu
    private Boolean canComment;  // Cho phép hoặc không cho phép bác sĩ và người dùng bình luận

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Date getSharedAt() {
        return sharedAt;
    }

    public void setSharedAt(Date sharedAt) {
        this.sharedAt = sharedAt;
    }

    public Boolean getCanComment() {
        return canComment;
    }

    public void setCanComment(Boolean canComment) {
        this.canComment = canComment;
    }
}
