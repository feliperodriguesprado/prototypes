package prototype.java.jsf.project1.model;

import java.util.Date;

public class CustomerTO {

    private String name;
    private String cpf;
    private Date birthday;
    private String phone;
    private String relationship;

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
        return "CustomerTO{" + "name=" + name + ", cpf=" + cpf + ", birthday=" + birthday + ", phone=" + phone + ", relationship=" + relationship + '}';
    }

}
