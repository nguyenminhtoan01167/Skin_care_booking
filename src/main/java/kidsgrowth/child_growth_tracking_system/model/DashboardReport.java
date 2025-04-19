package kidsgrowth.child_growth_tracking_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Entity
public class DashboardReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalConsultRequests;    // Tổng số yêu cầu tư vấn
    private Integer totalGrowthAlerts;       // Tổng số cảnh báo tăng trưởng
    private Integer totalUsers;              // Tổng số người dùng
    private Integer totalChildren;           // Tổng số trẻ em
    private Integer totalActiveMemberships;  // Tổng số gói thành viên đang hoạt động
    private Date reportDate;                 // Ngày báo cáo

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalConsultRequests() {
        return totalConsultRequests;
    }

    public void setTotalConsultRequests(Integer totalConsultRequests) {
        this.totalConsultRequests = totalConsultRequests;
    }

    public Integer getTotalGrowthAlerts() {
        return totalGrowthAlerts;
    }

    public void setTotalGrowthAlerts(Integer totalGrowthAlerts) {
        this.totalGrowthAlerts = totalGrowthAlerts;
    }

    public Integer getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Integer totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Integer getTotalChildren() {
        return totalChildren;
    }

    public void setTotalChildren(Integer totalChildren) {
        this.totalChildren = totalChildren;
    }

    public Integer getTotalActiveMemberships() {
        return totalActiveMemberships;
    }

    public void setTotalActiveMemberships(Integer totalActiveMemberships) {
        this.totalActiveMemberships = totalActiveMemberships;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}
