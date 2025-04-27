package ut.edu.skincarebooking.controller.authentication;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request) {
        System.out.println("Received register request: " + request.getEmail());
        try {
            Map<String, Object> response = authService.registerCustomer(request);
            if (response.containsKey("error")) {
                System.out.println("Registration error: " + response.get("error"));
                return ResponseEntity.badRequest().body(response);
            }
            System.out.println("Registration successful: " + request.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "An unexpected error occurred: " + e.getMessage());
            System.out.println("Controller error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(error);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        System.out.println("Received login request: " + request.getEmail());
        try {
            Map<String, Object> response = authService.login(request);
            if (response.containsKey("error")) {
                System.out.println("Login error: " + response.get("error"));
                return ResponseEntity.badRequest().body(response);
            }
            System.out.println("Login successful: " + request.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "An unexpected error occurred: " + e.getMessage());
            System.out.println("Controller error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(error);
        }
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
    // Xử lý logic logout (nếu cần)
    return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/register-manager")
    public ResponseEntity<Map<String, Object>> registerManager(@RequestBody RegisterRequest request) {
        System.out.println("Received register-manager request: " + request.getEmail());
        try {
            Map<String, Object> response = authService.registerManager(request);
            if (response.containsKey("error")) {
                System.out.println("Registration error: " + response.get("error"));
                return ResponseEntity.badRequest().body(response);
            }
            System.out.println("Manager registration successful: " + request.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "An unexpected error occurred: " + e.getMessage());
            System.out.println("Controller error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(error);
        }
    }
    


}


