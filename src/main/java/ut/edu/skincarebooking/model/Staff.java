package ut.edu.skincarebooking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Staff extends User {

    @Builder.Default
    private String userType = "STAFF";

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRole() == null) {
            setRole(Role.ROLE_STAFF);
        }
    }

    @Column(nullable = false, unique = true)
    private String username; // Username phải là duy nhất

    @Column(nullable = false, unique = true)
    private String email; // Email phải là duy nhất

    @Column(nullable = false)
    private String password; // Mật khẩu

    @Column(name = "img", nullable = false)
    private String img; // Ảnh đại diện của nhân viên
}