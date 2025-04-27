package ut.edu.skincarebooking.controller.page;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import ut.edu.skincarebooking.dto.request.ChangePasswordRequest;
import ut.edu.skincarebooking.service.AuthService;

@Controller
@PreAuthorize("hasRole('MANAGER')")
@RequestMapping("/protected/manager")
public class PageManagerController {
    @GetMapping("/")
    public String homeadmin() {
        return "admin/index"; // Tên file HTML trong thư mục templates
    }

    @Autowired
    private AuthService authService;
    @PostMapping("/edit-profile")
    public ResponseEntity<Map<String, Object>> editProfileManager(
            @RequestParam("currentEmail") String currentEmail,
            @RequestParam("name") String name,
            @RequestParam("email") String email) {
        System.out.println("Received edit-profile-manager request for email: " + currentEmail);
        try {
            Map<String, Object> response = authService.editProfileManager(currentEmail, name, email);
            if (response.containsKey("error")) {
                System.out.println("Edit profile error: " + response.get("error"));
                return ResponseEntity.badRequest().body(response);
            }
            System.out.println("Manager profile updated successfully: " + email);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "An unexpected error occurred: " + e.getMessage());
            System.out.println("Controller error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(error);
        }
    }
    @GetMapping("/profile")
    public String profilePage() {
        return "admin/profilemanager"; // Trả về file profile.html trong thư mục templates/admin
    }


    @PostMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePasswordManager(@Valid @RequestBody ChangePasswordRequest request, BindingResult bindingResult) {
        System.out.println("Received change-password-manager request for email: " + request.getEmail());
        // Xử lý lỗi kiểm tra dữ liệu
        if (bindingResult.hasErrors()) {
            Map<String, Object> response = new HashMap<>();
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            response.put("error", errorMessage);
            System.out.println("Validation error: " + errorMessage);
            return ResponseEntity.badRequest().body(response);
        }

        // Gọi phương thức changePasswordManager
        Map<String, Object> response = authService.changePasswordManager(request);
        if (response.containsKey("error")) {
            System.out.println("Change password error: " + response.get("error"));
            return ResponseEntity.badRequest().body(response);
        }
        System.out.println("Manager password changed successfully: " + request.getEmail());
        return ResponseEntity.ok(response);

    }

    @GetMapping("/list-customers")
    public ResponseEntity<Map<String, Object>> getAllCustomers() {
        System.out.println("Received request to get all customers");
        try {
            Map<String, Object> response = authService.getAllCustomers();
            if (response.containsKey("error")) {
                System.out.println("Error retrieving customers: " + response.get("error"));
                return ResponseEntity.badRequest().body(response);
            }
            System.out.println("Successfully retrieved customers");
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
