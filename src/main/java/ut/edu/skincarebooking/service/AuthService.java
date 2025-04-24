package ut.edu.skincarebooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerCustomer(RegisterRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        Customer customer = Customer.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
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
        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            return "Invalid password";
        }

        // TODO: Replace this with JWT token generation logic if needed
        // Example: Generate a JWT token and return it instead of a plain success message
        return "Login successful";
    }
}
