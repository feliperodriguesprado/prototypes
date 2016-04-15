package prototype.java.jsf.project2.models.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {

    private long id;
    
    @NotEmpty(message = "Username is required")
    private String userName;
    
    @NotEmpty(message = "Email is required")
    private String email;
    
    @NotEmpty(message = "Password is required")
    private String password;
    
    @NotEmpty(message = "Repeat password is required")
    private String repeatPassword;
    
    private PeopleDTO people;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public PeopleDTO getPeople() {
        return people;
    }

    public void setPeople(PeopleDTO people) {
        this.people = people;
    }

}
