package kidsgrowth.child_growth_tracking_system.model;
import jakarta.persistence.*;
import java.util.Date;
@Entity
public class GrowthAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // Mã cảnh báo duy nhất

    private long childId;  // ID của trẻ em (liên kết với bảng Children)
    private String alertType;  // Loại cảnh báo (ví dụ: chiều cao, cân nặng)
    private String alertMessage;  // Thông điệp cảnh báo
    private Date alertDate;  // Ngày cảnh báo được tạo
    private String status;  // Trạng thái cảnh báo (chưa xử lý, đã xử lý)

    @ManyToOne
    @JoinColumn(name = "growth_record_id")  // Liên kết với GrowthRecord
    private GrowthRecord growthRecord;  // Liên kết với GrowthRecord
    
    // Constructor mặc định
    public GrowthAlert() {}

    // Constructor với tham số
    public GrowthAlert(long childId, String alertType, String alertMessage, Date alertDate, String status) {
        this.childId = childId;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.alertDate = alertDate;
        this.status = status;
    }

    // Getter và Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
