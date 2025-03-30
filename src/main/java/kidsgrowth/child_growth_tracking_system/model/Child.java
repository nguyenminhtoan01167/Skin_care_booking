package kidsgrowth.child_growth_tracking_system.model;

import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String fullName;
    private String gender;
    private Date birthday;

    private Date createdAt;

    // Getters and Setters
    
}
