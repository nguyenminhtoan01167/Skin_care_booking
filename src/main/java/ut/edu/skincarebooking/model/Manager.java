package ut.edu.skincarebooking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "managers")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Manager extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id; // Sử dụng UUID làm ID

    @Column(nullable = false, unique = true)
    private String username; // Username phải là duy nhất

    @Column(nullable = false, unique = true)
    private String email; // Email phải là duy nhất

    @Column(nullable = false)
    private String password; // Mật khẩu

    private String name; // Tên của quản lý

    @Builder.Default
    private String userType = "MANAGER";

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRole() == null) {
            setRole(Role.ROLE_MANAGER);
        }
    }

    @Column(name = "img", nullable = false)
    private String img; // Ảnh đại diện của quản lý
}