package ut.edu.skincarebooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ut.edu.skincarebooking.dto.request.ChangePasswordRequest;
import ut.edu.skincarebooking.dto.request.LoginRequest;
import ut.edu.skincarebooking.dto.request.RegisterRequest;
import ut.edu.skincarebooking.model.Customer;
import ut.edu.skincarebooking.model.Manager;
import ut.edu.skincarebooking.repository.CustomerRepository;
import ut.edu.skincarebooking.repository.ManagerRepositoty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ManagerRepositoty managerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Map<String, Object> registerCustomer(RegisterRequest request) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("Register request: username=" + request.getUsername() + ", email=" + request.getEmail());

        try {
            // Chuẩn hóa email
            String email = request.getEmail() != null ? request.getEmail().trim().toLowerCase() : "";
            if (email.isEmpty()) {
                response.put("error", "Email is required");
                System.out.println("Email is empty");
                return response;
            }

            // Kiểm tra email đã tồn tại
            if (customerRepository.existsByEmail(email)) {
                response.put("error", "Email already exists");
                System.out.println("Email already exists: " + email);
                return response;
            }

            // Kiểm tra username đã tồn tại
            String username = request.getUsername() != null ? request.getUsername().trim() : "";
            if (username.isEmpty()) {
                response.put("error", "Username is required");
                System.out.println("Username is empty");
                return response;
            }
            if (customerRepository.existsByUsername(username)) {
                response.put("error", "Username already exists");
                System.out.println("Username already exists: " + username);
                return response;
            }

            // Kiểm tra password
            String password = request.getPassword();
            if (password == null || password.trim().isEmpty()) {
                response.put("error", "Password is required");
                System.out.println("Password is empty");
                return response;
            }

            // Mã hóa mật khẩu
            String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
            System.out.println("Password encoded");

            // Tạo đối tượng Customer
            Customer customer = Customer.builder()
                    .username(username)
                    .email(email)
                    .password(encodedPassword)
                    .role(Customer.Role.ROLE_CUSTOMER)
                    .userType("CUSTOMER")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            // Lưu vào cơ sở dữ liệu
            customerRepository.save(customer);
            System.out.println("Customer saved: " + email);

            // Trả về thông tin người dùng
            Map<String, String> user = new HashMap<>();
            user.put("username", customer.getUsername());
            user.put("email", customer.getEmail());

            response.put("user", user);
            response.put("message", "Customer registered successfully");

            return response;
        } catch (Exception e) {
            response.put("error", "Failed to register customer: " + e.getMessage());
            System.out.println("Registration error: " + e.getMessage());
            e.printStackTrace();
            return response;
        }
    }


    public Map<String, Object> login(LoginRequest request) {
        Map<String, Object> response = new HashMap<>();
        String email = request.getEmail() != null ? request.getEmail().trim().toLowerCase() : "";
        System.out.println("Login request for email: " + email);

        // Kiểm tra email trong bảng customers
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (!new BCryptPasswordEncoder().matches(request.getPassword(), customer.getPassword())) {
                response.put("error", "Invalid password");
                System.out.println("Invalid password for email: " + email);
                return response;
            }

            Map<String, String> user = new HashMap<>();
            user.put("username", customer.getUsername());
            user.put("email", customer.getEmail());
            user.put("role", customer.getRole().name());

            response.put("user", user);
            response.put("isCustomer", true);
            response.put("isManager", false);
            response.put("message", "Login successful");
            return response;
        }

        // Kiểm tra email trong bảng managers
        Optional<Manager> managerOptional = managerRepository.findByEmail(email);
        if (managerOptional.isPresent()) {
            Manager manager = managerOptional.get();
            if (!new BCryptPasswordEncoder().matches(request.getPassword(), manager.getPassword())) {
                response.put("error", "Invalid password");
                System.out.println("Invalid password for email: " + email);
                return response;
            }

            Map<String, String> user = new HashMap<>();
            user.put("username", manager.getUsername());
            user.put("email", manager.getEmail());
            user.put("role", manager.getRole().name());

            response.put("user", user);
            response.put("isCustomer", false);
            response.put("isManager", true);
            response.put("message", "Login successful");
            return response;
        }

        response.put("error", "Email not found");
        System.out.println("Email not found: " + email);
        return response;
    }
public Map<String, Object> changePassword(ChangePasswordRequest request) {
    Map<String, Object> response = new HashMap<>();

    // Chuẩn hóa email
    String email = request.getEmail() != null ? request.getEmail().trim().toLowerCase() : "";

    // Tìm người dùng theo email
    Optional<Customer> customerOptional = customerRepository.findByEmail(email);
    if (customerOptional.isEmpty()) {
        response.put("error", "User not found");
        return response;
    }

    Customer customer = customerOptional.get();

    // Kiểm tra mật khẩu hiện tại
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (!passwordEncoder.matches(request.getCurrentPassword(), customer.getPassword())) {
        response.put("error", "Current password is incorrect");
        return response;
    }

    // Kiểm tra mật khẩu mới và xác nhận mật khẩu
    if (!request.getNewPassword().equals(request.getConfirmPassword())) {
        response.put("error", "New password and confirm password do not match");
        return response;
    }

    // Mã hóa mật khẩu mới
    String encodedPassword = passwordEncoder.encode(request.getNewPassword());

    // Cập nhật mật khẩu
    customer.setPassword(encodedPassword);
    customerRepository.save(customer);

    // Trả về thông tin phản hồi
    Map<String, String> user = new HashMap<>();
    user.put("username", customer.getUsername());
    user.put("email", customer.getEmail());

    response.put("user", user);
    response.put("message", "Password changed successfully");

    return response;
}
    public Map<String, Object> editProfile(String currentEmail, String username, String email) {
        Map<String, Object> response = new HashMap<>();

        // Tìm khách hàng theo email hiện tại
        Optional<Customer> customerOptional = customerRepository.findByEmail(currentEmail);
        if (customerOptional.isEmpty()) {
            response.put("error", "User not found");
            return response;
        }

        Customer customer = customerOptional.get();

        // Kiểm tra email mới có trùng với email khác không
        if (!email.equals(currentEmail) && customerRepository.existsByEmail(email)) {
            response.put("error", "Email already exists");
            return response;
        }

        // Kiểm tra username mới có trùng không
        if (!username.equals(customer.getUsername()) && customerRepository.existsByUsername(username)) {
            response.put("error", "Username already exists");
            return response;
        }

        // Cập nhật thông tin
        customer.setUsername(username);
        customer.setEmail(email);

        // Lưu vào cơ sở dữ liệu
        customerRepository.save(customer);

        // Trả về thông tin người dùng đã cập nhật
        Map<String, String> user = new HashMap<>();
        user.put("username", customer.getUsername());
        user.put("email", customer.getEmail());

        response.put("user", user);
        response.put("message", "Profile updated successfully");

        return response;
    }


    public Map<String, Object> registerManager(RegisterRequest request) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("Register manager request: username=" + request.getUsername() + ", email=" + request.getEmail());

        try {
            // Chuẩn hóa email
            String email = request.getEmail() != null ? request.getEmail().trim().toLowerCase() : "";
            if (email.isEmpty()) {
                response.put("error", "Email is required");
                System.out.println("Email is empty");
                return response;
            }

            // Kiểm tra email đã tồn tại
            if (managerRepository.existsByEmail(email)) {
                response.put("error", "Email already exists");
                System.out.println("Email already exists: " + email);
                return response;
            }

            // Kiểm tra username đã tồn tại
            String username = request.getUsername() != null ? request.getUsername().trim() : "";
            if (username.isEmpty()) {
                response.put("error", "Username is required");
                System.out.println("Username is empty");
                return response;
            }
            if (managerRepository.existsByUsername(username)) {
                response.put("error", "Username already exists");
                System.out.println("Username already exists: " + username);
                return response;
            }

            // Kiểm tra password
            String password = request.getPassword();
            if (password == null || password.trim().isEmpty()) {
                response.put("error", "Password is required");
                System.out.println("Password is empty");
                return response;
            }

            // Mã hóa mật khẩu
            String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
            System.out.println("Password encoded");

            // Tạo đối tượng Customer với vai trò ROLE_MANAGER
            Manager manager = Manager.builder()
                    .username(username)
                    .email(email)
                    .password(encodedPassword)
                    .role(Manager.Role.ROLE_MANAGER)
                    .userType("MANAGER")
                    .img("default-image-url.jpg")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            // Lưu vào cơ sở dữ liệu
            managerRepository.save(manager);
            System.out.println("Manager saved: " + email);

            // Trả về thông tin người dùng
            Map<String, String> user = new HashMap<>();
            user.put("username", manager.getUsername());
            user.put("email", manager.getEmail());

            response.put("user", user);
            response.put("message", "Manager registered successfully");

            return response;
        } catch (Exception e) {
            response.put("error", "Failed to register manager: " + e.getMessage());
            System.out.println("Registration error: " + e.getMessage());
            e.printStackTrace();
            return response;
        }
    }
    public Map<String, Object> editProfileManager(String currentEmail, String name, String email) {
        Map<String, Object> response = new HashMap<>();

        // Tìm quản lý theo email hiện tại
        Optional<Manager> managerOptional = managerRepository.findByEmail(currentEmail);
        if (managerOptional.isEmpty()) {
            response.put("error", "User not found");
            System.out.println("Manager not found for email: " + currentEmail);
            return response;
        }

        Manager manager = managerOptional.get();

        // Kiểm tra email mới có trùng với email khác không
        if (!email.equals(currentEmail) && managerRepository.existsByEmail(email)) {
            response.put("error", "Email already exists");
            System.out.println("Email already exists: " + email);
            return response;
        }

        // Kiểm tra username mới có trùng không
        if (!name.equals(manager.getUsername()) && managerRepository.existsByUsername(name)) {
            response.put("error", "Username already exists");
            System.out.println("Username already exists: " + name);
            return response;
        }

        // Cập nhật thông tin
        manager.setUsername(name);
        manager.setEmail(email);

        // Lưu vào cơ sở dữ liệu
        managerRepository.save(manager);

        // Trả về thông tin người dùng đã cập nhật
        Map<String, String> user = new HashMap<>();
        user.put("username", manager.getUsername());
        user.put("email", manager.getEmail());

        response.put("user", user);
        response.put("message", "Manager profile updated successfully");
        System.out.println("Manager profile updated: " + email);
        return response;
    }

    public Map<String, Object> changePasswordManager(ChangePasswordRequest request) {
        Map<String, Object> response = new HashMap<>();

        // Chuẩn hóa email
        String email = request.getEmail() != null ? request.getEmail().trim().toLowerCase() : "";

        // Tìm người dùng theo email
        Optional<Manager> managerOptional = managerRepository.findByEmail(email);
        if (managerOptional.isEmpty()) {
            response.put("error", "User not found");
            System.out.println("Manager not found for email: " + email);
            return response;
        }

        Manager manager = managerOptional.get();

        // Kiểm tra mật khẩu hiện tại
        if (!new BCryptPasswordEncoder().matches(request.getCurrentPassword(), manager.getPassword())) {
            response.put("error", "Current password is incorrect");
            System.out.println("Invalid current password for email: " + email);
            return response;
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            response.put("error", "New password and confirm password do not match");
            System.out.println("New password and confirm password do not match for email: " + email);
            return response;
        }

        // Mã hóa mật khẩu mới
        String encodedPassword = new BCryptPasswordEncoder().encode(request.getNewPassword());

        // Cập nhật mật khẩu
        manager.setPassword(encodedPassword);
        managerRepository.save(manager);

        // Trả về thông tin phản hồi
        Map<String, String> user = new HashMap<>();
        user.put("username", manager.getUsername());
        user.put("email", manager.getEmail());

        response.put("user", user);
        response.put("message", "Manager password changed successfully");
        System.out.println("Manager password changed successfully for email: " + email);

        return response;
    }

    public Map<String, Object> getAllCustomers() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Customer> customers = customerRepository.findAll();
            List<Map<String, String>> customerList = new ArrayList<>();

            for (Customer customer : customers) {
                Map<String, String> customerData = new HashMap<>();
                customerData.put("username", customer.getUsername());
                customerData.put("email", customer.getEmail());
                customerData.put("role", customer.getRole().name());
                customerList.add(customerData);
            }

            response.put("customers", customerList);
            response.put("message", "Retrieved all customers successfully");
            System.out.println("Retrieved " + customerList.size() + " customers");
            return response;
        } catch (Exception e) {
            response.put("error", "Failed to retrieve customers: " + e.getMessage());
            System.out.println("Error retrieving customers: " + e.getMessage());
            e.printStackTrace();
            return response;
        }
    }
    
}

    // Các phương thức khác như registerCustomer, loginCustomer...
