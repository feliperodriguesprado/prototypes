package prototype.java.jsf.project2.user.core.models.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import prototype.java.jsf.project2.user.api.enums.PeopleType;

@Entity
@Table(name = "people")
@SequenceGenerator(name = "peopleSequence", sequenceName = "people_id_seq", allocationSize = 1)
public class PeoplePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peopleSequence")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private int type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PeopleType getType() {
        return PeopleType.parse(type);
    }

    public void setType(PeopleType type) {
        this.type = type.getCode();
    }

    @Override
    public String toString() {
        return "PeoplePO{" + "id=" + id + ", name=" + name + ", type=" + type + '}';
    }

}
