package user.api.models.dto;

public class UserDTO {

    private long Id;
    private String userName;
    private String email;
    private String password;
    private String repeatPassword;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
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
        return "UserDTO{" + "Id=" + Id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", repeatPassword=" + repeatPassword + '}';
    }

}
