package kidsgrowth.child_growth_tracking_system.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kidsgrowth.child_growth_tracking_system.repository.ChildRepository;
import kidsgrowth.child_growth_tracking_system.repository.DoctorRepository;
import kidsgrowth.child_growth_tracking_system.repository.UserRepository;

@Service
public class DashboardStats {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ChildRepository childRepository;

    private long totalUsers;
    private long totalDoctors;
    private long totalChildren;

    // Constructor mặc định
    public DashboardStats() {}

    // Getter và Setter cho các thuộc tính
    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalDoctors() {
        return totalDoctors;
    }

    public void setTotalDoctors(long totalDoctors) {
        this.totalDoctors = totalDoctors;
    }

    public long getTotalChildren() {
        return totalChildren;
    }

    public void setTotalChildren(long totalChildren) {
        this.totalChildren = totalChildren;
    }

    // Phương thức lấy thống kê từ cơ sở dữ liệu
    public void updateStats() {
        // Sử dụng Spring Data JPA để lấy số lượng người dùng, bác sĩ và trẻ em
        this.totalUsers = userRepository.count();
        this.totalDoctors = doctorRepository.count();
        this.totalChildren = childRepository.count();
    }
}