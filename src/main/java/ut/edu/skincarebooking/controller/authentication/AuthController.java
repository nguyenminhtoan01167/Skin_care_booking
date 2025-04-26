package ut.edu.skincarebooking.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ut.edu.skincarebooking.dto.request.LoginRequest;
import ut.edu.skincarebooking.dto.request.RegisterRequest;
import ut.edu.skincarebooking.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerCustomer(request));
    }

    @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    try {
        return ResponseEntity.ok(authService.loginCustomer(request));
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(500).body("An unexpected error occurred.");
    }
}


    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
    // Xử lý logic logout (nếu cần)
    return ResponseEntity.ok("Logged out successfully");
    }
}
