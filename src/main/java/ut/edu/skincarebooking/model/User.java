package ut.edu.skincarebooking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass // Lớp này không tạo bảng, chỉ để các lớp con kế thừa
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public enum Role {
        ROLE_CUSTOMER,
        ROLE_MANAGER,
        ROLE_STAFF,
        ROLE_THERAPIST
    }

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Thêm phương thức tiện ích để kiểm tra vai trò
    public boolean isCustomer() {
        return Role.ROLE_CUSTOMER.equals(this.role);
    }

    public boolean isManager() {
        return Role.ROLE_MANAGER.equals(this.role);
    }

    public boolean isStaff() {
        return Role.ROLE_STAFF.equals(this.role);
    }

    public boolean isTherapist() {
        return Role.ROLE_THERAPIST.equals(this.role);
    }
}