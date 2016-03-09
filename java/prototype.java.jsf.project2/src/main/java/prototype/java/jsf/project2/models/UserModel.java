package prototype.java.jsf.project2.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @NotEmpty(message = "User ID is required")
    private Long userId;

    @Column(name = "username")
    @NotEmpty(message = "Username is required")
    private String userName;

    @Column(name = "email")
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password is required")
    private String password;

    @Column(name = "repeat_password")
    @NotEmpty(message = "Repeat password is required")
    private String repeatPassword;

    // References:
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Column(name = "people")
    private PeopleModel people;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public PeopleModel getPeople() {
        return people;
    }

    public void setPeople(PeopleModel people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "UserModel{" + "userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password + ", repeatPassword=" + repeatPassword + ", people=" + people + '}';
    }

}
