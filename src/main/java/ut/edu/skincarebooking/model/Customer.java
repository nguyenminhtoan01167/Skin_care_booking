package ut.edu.skincarebooking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {

    @Builder.Default
    private String userType = "CUSTOMER";

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRole() == null) {
            setRole(Role.ROLE_CUSTOMER);
        }
    }

    @Column(name = "account_verified", nullable = false)
    private boolean accountVerified; // Trạng thái xác minh tài khoản
}