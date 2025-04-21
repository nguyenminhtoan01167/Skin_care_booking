package kidsgrowth.child_growth_tracking_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/register")
    public String showRegisterPage() {
        return "master/register";
    }

    @GetMapping("/")
    public String showHomePage() {
        return "master/index";
    }

    @GetMapping("/login")
    public String shwLoginPage() {
        return "master/login";
    }
}
