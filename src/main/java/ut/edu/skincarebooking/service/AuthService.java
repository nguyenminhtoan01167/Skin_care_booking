package ut.edu.skincarebooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.edu.skincarebooking.dto.request.ChangePasswordRequest;
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

     public void changePassword(ChangePasswordRequest request) {
        // Tìm người dùng theo email
        Optional<Customer> customerOptional = customerRepository.findByEmail(request.getEmail());
        if (customerOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Customer customer = customerOptional.get();

        // Kiểm tra mật khẩu hiện tại (so sánh chuỗi trực tiếp)
        if (!request.getCurrentPassword().equals(customer.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("New password and confirm password do not match");
        }

        // Cập nhật mật khẩu (lưu trực tiếp chuỗi mới)
        customer.setPassword(request.getNewPassword());
        customerRepository.save(customer);
    }
}