package prototype.java.jsf.project2.user.core.models.po;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "userSequence", sequenceName = "user_id_seq", allocationSize = 1)
public class UserPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private long id;

    @NotEmpty(message = "Username is required")
    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password is required")
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "people_id")
    private PeoplePO people;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PeoplePO getPeople() {
        return people;
    }

    public void setPeople(PeoplePO people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "UserPO{" + "id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", people=" + people + '}';
    }

}
