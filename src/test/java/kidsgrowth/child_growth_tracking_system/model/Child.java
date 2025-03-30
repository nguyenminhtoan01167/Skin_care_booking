package kidsgrowth.child_growth_tracking_system.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User parent;
    
    @Column(name = "FullName", nullable = false, length = 100)
    private String fullName;
    
    @Column(name = "Gender", nullable = false, length = 10)
    private String gender;
    
    @Column(name = "Birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Column(name = "CreatedAt", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    // Constructors
    public Child() {}

    public Child(User parent, String fullName, String gender, Date birthday) {
        this.parent = parent;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}