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
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("/protected/customer")
public class PageCustomerController {
    

    @GetMapping("/")
    public String home() {
        return "user/index"; // Tên file HTML trong thư mục templates
    }

    @GetMapping("/about")
    public String about() {
        return "user/about"; // Tên file HTML trong thư mục templates
    }
    @GetMapping("/contact")
    public String contact() {
        return "user/contact"; // Tên file HTML trong thư mục templates
    }

   @GetMapping("/resume")
   public String resume() {
       return "user/resume"; // Tên file HTML trong thư mục templates
   }

    @GetMapping("/portfolio")
    public String portfolio() {
        return "user/portfolio"; // Tên file HTML trong thư mục templates
    }
    @GetMapping("/services")
    public String services() {
        return "user/services"; // Tên file HTML trong thư mục templates
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "user/profile"; // Trả về file profile.html trong thư mục templates/admin
    }
    @Autowired
    private AuthService authService;
    @PostMapping("/edit-profile")
    public ResponseEntity<Map<String, Object>> editProfile(
            @RequestParam("currentEmail") String currentEmail,
            @RequestParam("name") String username,
            @RequestParam("email") String email) {
        try {
            Map<String, Object> response = authService.editProfile(currentEmail, username, email);
            if (response.containsKey("error")) {
                return ResponseEntity.badRequest().body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    @Autowired
    private AuthService customerService;
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(@Valid @RequestBody ChangePasswordRequest request, BindingResult bindingResult) {
        // Xử lý lỗi kiểm tra dữ liệu
        if (bindingResult.hasErrors()) {
            Map<String, Object> response = new HashMap<>();
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            response.put("error", errorMessage);
            return ResponseEntity.badRequest().body(response);
        }

        // Gọi phương thức changePassword
        Map<String, Object> response = customerService.changePassword(request);
        if (response.containsKey("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
    

    
   

    
}
