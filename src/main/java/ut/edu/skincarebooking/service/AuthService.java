package ut.edu.skincarebooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ut.edu.skincarebooking.dto.request.ChangePasswordRequest;
import ut.edu.skincarebooking.dto.request.LoginRequest;
import ut.edu.skincarebooking.dto.request.RegisterRequest;
import ut.edu.skincarebooking.model.Customer;
import ut.edu.skincarebooking.repository.CustomerRepository;
import ut.edu.skincarebooking.repository.ManagerRepository;
import ut.edu.skincarebooking.repository.SkinTherapistRepository;
import ut.edu.skincarebooking.repository.StaffRepository;
import ut.edu.skincarebooking.model.Manager;
import ut.edu.skincarebooking.model.SkinTherapist;
import ut.edu.skincarebooking.model.Staff;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    public String registerCustomer(RegisterRequest request) {
    // Kiểm tra email đã tồn tại
    if (customerRepository.existsByEmail(request.getEmail())) {
        throw new IllegalArgumentException("Email already exists");
    }

    // Kiểm tra username đã tồn tại
    if (customerRepository.existsByEmail(request.getUsername())) {
        throw new IllegalArgumentException("Username already exists");
    }

    // Mã hóa mật khẩu
    String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

    // Tạo đối tượng Customer
    Customer customer = Customer.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(encodedPassword) // Lưu mật khẩu đã mã hóa
            .build();

    // Lưu vào cơ sở dữ liệu
    customerRepository.save(customer);

    return "Customer registered successfully";
}
@Autowired
private ManagerRepository managerRepository;

public String registerManager(RegisterRequest request) {
    // Kiểm tra email đã tồn tại
    if (managerRepository.existsByEmail(request.getEmail())) {
        throw new IllegalArgumentException("Email already exists");
    }

    // Kiểm tra username đã tồn tại
    if (managerRepository.existsByUsername(request.getUsername())) {
        throw new IllegalArgumentException("Username already exists");
    }

    // Mã hóa mật khẩu
    String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

    // Tạo đối tượng Manager
    Manager manager = Manager.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(encodedPassword) // Lưu mật khẩu đã mã hóa
            .build();

    // Lưu vào cơ sở dữ liệu
    managerRepository.save(manager);

    return "Manager registered successfully";
}
@Autowired
private SkinTherapistRepository skinTherapistRepository;

public String registerSkinTherapist(RegisterRequest request) {
    // Kiểm tra email đã tồn tại
    if (skinTherapistRepository.existsByEmail(request.getEmail())) {
        throw new IllegalArgumentException("Email already exists");
    }

    // Kiểm tra username đã tồn tại
    if (skinTherapistRepository.existsByUsername(request.getUsername())) {
        throw new IllegalArgumentException("Username already exists");
    }

    // Mã hóa mật khẩu
    String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

    // Tạo đối tượng SkinTherapist
    SkinTherapist skinTherapist = SkinTherapist.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(encodedPassword) // Lưu mật khẩu đã mã hóa
            .build();

    // Lưu vào cơ sở dữ liệu
    skinTherapistRepository.save(skinTherapist);

    return "Skin Therapist registered successfully";
}
@Autowired
private StaffRepository staffRepository;

public String registerStaff(RegisterRequest request) {
    // Kiểm tra email đã tồn tại
    if (staffRepository.existsByEmail(request.getEmail())) {
        throw new IllegalArgumentException("Email already exists");
    }

    // Kiểm tra username đã tồn tại
    if (staffRepository.existsByUsername(request.getUsername())) {
        throw new IllegalArgumentException("Username already exists");
    }

    // Mã hóa mật khẩu
    String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

    // Tạo đối tượng Staff
    Staff staff = Staff.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(encodedPassword) // Lưu mật khẩu đã mã hóa
            .build();

    // Lưu vào cơ sở dữ liệu
    staffRepository.save(staff);

    return "Staff registered successfully";
}
@Autowired
private StaffRepository userRepository;

public String registerUser(RegisterRequest request) {

    // Kiểm tra email đã tồn tại
    if (userRepository.existsByEmail(request.getEmail())) {
        throw new IllegalArgumentException("Email already exists");
    }

    // Kiểm tra username đã tồn tại
    if (userRepository.existsByUsername(request.getUsername())) {
        throw new IllegalArgumentException("Username already exists");
    }

    // Mã hóa mật khẩu
    String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

    // Tạo đối tượng User
    Staff user = Staff.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(encodedPassword) // Lưu mật khẩu đã mã hóa
            .build();

    // Lưu vào cơ sở dữ liệu
    userRepository.save(user);

    return "User registered successfully";
}
public String loginCustomer(LoginRequest request) {
    // Tìm người dùng theo username
    Optional<Customer> customerOptional = customerRepository.findByEmail(request.getEmail());

    if (customerOptional.isEmpty()) {
        return "Email not found";
    }

    Customer customer = customerOptional.get();

    // Kiểm tra mật khẩu (so sánh mật khẩu đã mã hóa)
    if (!new BCryptPasswordEncoder().matches(request.getPassword(), customer.getPassword())) {
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
    // Xóa một trong hai phương thức

}