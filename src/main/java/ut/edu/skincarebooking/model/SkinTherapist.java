package ut.edu.skincarebooking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skin_therapists")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SkinTherapist extends User {

    @Builder.Default
    private String userType = "THERAPIST";

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRole() == null) {
            setRole(Role.ROLE_THERAPIST);
        }
    }

    @Column(name = "specialty", nullable = false)
    private String specialty; // Chuyên môn của chuyên viên

    @Column(name = "img", nullable = false)
    private String img; // Ảnh đại diện của chuyên viên
}