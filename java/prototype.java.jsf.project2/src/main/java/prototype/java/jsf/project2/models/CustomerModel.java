package prototype.java.jsf.project2.models;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class CustomerModel {

    private int id;

    @NotEmpty(message = "Client name is required")
    private String name;

    @Pattern(regexp = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message = "CPF is required")
    private String cpf;

    @NotNull(message = "Birthday is required")
    private Date birthday;

    private String phone;

    @NotEmpty(message = "Relationship is required")
    private String relationship;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "CustomerModel{" + "id=" + id + ", name=" + name + ", cpf=" + cpf + ", birthday=" + birthday + ", phone=" + phone + ", relationship=" + relationship + '}';
    }

}
