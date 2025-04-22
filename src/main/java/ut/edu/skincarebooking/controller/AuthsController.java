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
//    public String portfoliodetails() {
//        return "about"; // Tên file HTML trong thư mục templates
//    }
//    @GetMapping("/about")
//    public String portfolio() {
//        return "about"; // Tên file HTML trong thư mục templates
//    }
//    @GetMapping("/about")
//    public String resume() {
//        return "about"; // Tên file HTML trong thư mục templates
//    }
}
