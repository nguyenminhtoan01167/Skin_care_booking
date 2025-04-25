package ut.edu.skincarebooking.controller.page;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("/protected/customer")
public class PageCustomerController {
    
}
