package kidsgrowth.child_growth_tracking_system.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class UserMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // Mã định danh duy nhất của tư cách thành viên

    private long userId;  // Liên kết với User (ID của người dùng)
    private String membershipType;  // Loại thành viên (ví dụ: miễn phí, trả phí)
    private Date startDate;  // Ngày bắt đầu tư cách thành viên
    private Date endDate;  // Ngày hết hạn tư cách thành viên
    private String status;  // Trạng thái của thành viên (active, expired)

    // Constructor mặc định
    public UserMembership() {}

    // Constructor với tham số
    public UserMembership(long userId, String membershipType, Date startDate, Date endDate, String status) {
        this.userId = userId;
        this.membershipType = membershipType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getter và Setter cho các thuộc tính
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
