package ut.edu.skincarebooking.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private AuthService customerService;
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            customerService.changePassword(request);
            return ResponseEntity.ok("Password changed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }
    
}
