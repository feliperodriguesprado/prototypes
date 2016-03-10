package prototype.java.jsf.project2.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class UserModel extends PeopleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Username is required")
    private String userName;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Repeat password is required")
    private String repeatPassword;

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

    @Override
    public String toString() {
        return "UserModel {" + "peopleId=" + getPeopleId() + ", userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password + ", repeatPassword=" + repeatPassword + '}';
    }

}
