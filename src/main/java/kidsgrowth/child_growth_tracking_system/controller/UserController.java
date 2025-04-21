package kidsgrowth.child_growth_tracking_system.controller;

import kidsgrowth.child_growth_tracking_system.model.User;
import kidsgrowth.child_growth_tracking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")  // API
public class UserController {

    @Autowired
    private UserService userService;

    // API Đổi Mật Khẩu
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam Long userId,
                                                  @RequestParam String oldPassword,
                                                  @RequestParam String newPassword) {
        boolean isChanged = userService.changePassword(userId, oldPassword, newPassword);
        if (isChanged) {
            return ResponseEntity.ok("Mật khẩu đã được thay đổi thành công.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu cũ không chính xác hoặc có lỗi xảy ra.");
        }
    }

    // API Lấy Thông Tin Hồ Sơ
    @GetMapping("/profile/{userId}")
    public ResponseEntity<User> getProfile(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Đăng ký người dùng
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Lấy thông tin người dùng theo email
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Lấy tất cả người dùng
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Xóa người dùng
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
