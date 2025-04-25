package ut.edu.skincarebooking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "customers")
public class Customer extends User {

    @Builder.Default
    private String userType = "CUSTOMER";
    @Column(nullable = false, unique = true)
    private String username; // Thêm trường username với ràng buộc unique

    @Column(nullable = false, unique = true)
    private String email; // Thêm trường email với ràng buộc unique

    @Column(nullable = false)
    private String password; // Thêm trường password
    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRole() == null) {
            setRole(Role.ROLE_CUSTOMER);
        }
    }
}