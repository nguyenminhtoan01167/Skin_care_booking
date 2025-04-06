package kidsgrowth.child_growth_tracking_system.service;

import kidsgrowth.child_growth_tracking_system.model.User;
import kidsgrowth.child_growth_tracking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Tạo mới người dùng
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Lấy người dùng theo email
    // public User getUserByEmail(String email) {
    //     return userRepository.findByEmail(email);
    // }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);  // Trả về null nếu không tìm thấy người dùng
    }

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Cập nhật thông tin người dùng
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    // Xóa người dùng
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
}
