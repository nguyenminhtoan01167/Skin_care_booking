package kidsgrowth.child_growth_tracking_system.model;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;

import java.util.Date;
@Entity
public class HealthReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;  // Liên kết với bảng Child (trẻ em)

    private Date reportDate;  // Ngày báo cáo
    private Float heightCm;   // Chiều cao (cm)
    private Float weightKg;   // Cân nặng (kg)
    private Float bmi;        // BMI (Chỉ số khối cơ thể)
    
    @Enumerated(EnumType.STRING)
    private Status status;    // Trạng thái sức khỏe của trẻ (Normal, Underweight, Overweight, Obese)
    
    private String reportSummary;  // Tóm tắt báo cáo sức khỏe

    public enum Status {
        NORMAL, UNDERWEIGHT, OVERWEIGHT, OBESE
    }

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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Float getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(Float heightCm) {
        this.heightCm = heightCm;
    }

    public Float getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Float weightKg) {
        this.weightKg = weightKg;
    }

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReportSummary() {
        return reportSummary;
    }

    public void setReportSummary(String reportSummary) {
        this.reportSummary = reportSummary;
    }
}
