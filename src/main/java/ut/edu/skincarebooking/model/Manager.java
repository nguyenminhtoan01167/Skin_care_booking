package ut.edu.skincarebooking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "managers")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Manager extends User {

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