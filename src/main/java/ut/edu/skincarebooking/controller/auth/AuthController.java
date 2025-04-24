package ut.edu.skincarebooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        String result = authService.registerCustomer(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String result = authService.loginCustomer(request);
        return ResponseEntity.ok(result);
    }
}
