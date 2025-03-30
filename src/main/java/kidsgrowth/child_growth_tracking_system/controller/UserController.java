package kidsgrowth.child_growth_tracking_system.controller;

import kidsgrowth.child_growth_tracking_system.model.User;
import kidsgrowth.child_growth_tracking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")  // Đặt đường dẫn cho API
public class UserController {
    
    @Autowired
    private UserService userService;  // Inject service để xử lý nghiệp vụ

    // Đăng ký người dùng mới
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);  // Trả về người dùng vừa được tạo
    }

    // Lấy thông tin người dùng theo email
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        
        if (user == null) {
            return ResponseEntity.notFound().build();  // Trả về 404 nếu không tìm thấy người dùng
        }
        
        return ResponseEntity.ok(user);  // Trả về thông tin người dùng nếu tìm thấy
    }
}
