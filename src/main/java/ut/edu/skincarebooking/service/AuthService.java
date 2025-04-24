package ut.edu.skincarebooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ut.edu.skincarebooking.dto.request.LoginRequest;
import ut.edu.skincarebooking.dto.request.RegisterRequest;
import ut.edu.skincarebooking.model.Customer;
import ut.edu.skincarebooking.repository.CustomerRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    public String registerCustomer(RegisterRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        Customer customer = Customer.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // Lưu mật khẩu trực tiếp
                .build();

        customerRepository.save(customer);

        return "Customer registered successfully";
    }

    public String loginCustomer(LoginRequest request) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(request.getEmail());

        if (customerOptional.isEmpty()) {
            return "Email not found";
        }

        Customer customer = customerOptional.get();
        if (!request.getPassword().equals(customer.getPassword())) { // So sánh mật khẩu trực tiếp
            return "Invalid password";
        }

        return "Login successful";
    }
}