package ut.edu.skincarebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthsController {
    @GetMapping("/about")
    public String about() {
        return "about"; // Tên file HTML trong thư mục templates
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact"; // Tên file HTML trong thư mục templates
    }
//    @GetMapping("/about")
//    public String about() {
//        return "about"; // Tên file HTML trong thư mục templates
//    }
//    @GetMapping("/about")
//    public String portfolio() {
//        return "about"; // Tên file HTML trong thư mục templates
//    }
   @GetMapping("/resume")
   public String resume() {
       return "resume"; // Tên file HTML trong thư mục templates
   }

    @GetMapping("/portfolio")
    public String portfolio() {
        return "portfolio"; // Tên file HTML trong thư mục templates
    }
    @GetMapping("/services")
    public String services() {
        return "services"; // Tên file HTML trong thư mục templates
    }


    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin/html/dashboard/index"; // Trả về file index.html trong thư mục templates/admin/html/dashboard
    }
}