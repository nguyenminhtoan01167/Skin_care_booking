package ut.edu.skincarebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllers {

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

    @GetMapping("/login")
    public String Login() {
       return "user/auth-login-basic"; // Tên file HTML trong thư mục templates
    }
    @GetMapping("/register")
    public String Register() {
       return "user/auth-register-basic"; // Tên file HTML trong thư mục templates
   }

    


    
    
    
}