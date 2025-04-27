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

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRole() == null) {
            setRole(Role.ROLE_CUSTOMER);
        }
    }
    @Column(name = "img", nullable = true) // Cho phép null
    private String img;
}