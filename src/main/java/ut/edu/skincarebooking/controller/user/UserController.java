package ut.edu.skincarebooking.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ut.edu.skincarebooking.dto.user.UserDTO;
import ut.edu.skincarebooking.model.Customer;
import ut.edu.skincarebooking.model.Staff;
import ut.edu.skincarebooking.model.SkinTherapist;
import ut.edu.skincarebooking.repository.CustomerRepository;
import ut.edu.skincarebooking.repository.StaffRepository;
import ut.edu.skincarebooking.repository.SkinTherapistRepository;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private SkinTherapistRepository skinTherapistRepository;

    // API lấy danh sách tất cả Customer
    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>> getAllCustomers() {
        List<UserDTO> customers = customerRepository.findAll().stream()
                .map(customer -> new UserDTO(customer.getUsername(), customer.getEmail(), customer.getUserType()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(customers);
    }
    // API lấy danh sách tất cả Staff
    @GetMapping("/staffs")
    public ResponseEntity<List<UserDTO>> getAllStaffs() {
        List<UserDTO> staffs = staffRepository.findAll().stream()
                .map(staff -> new UserDTO(staff.getUsername(), staff.getEmail(), staff.getUserType()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(staffs);
    }

    // API lấy danh sách tất cả SkinTherapist
    @GetMapping("/skintherapists")
    public ResponseEntity<List<UserDTO>> getAllSkinTherapists() {
        List<UserDTO> skinTherapists = skinTherapistRepository.findAll().stream()
                .map(therapist -> new UserDTO(therapist.getUsername(), therapist.getEmail(), therapist.getUserType()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(skinTherapists);
    }

}
