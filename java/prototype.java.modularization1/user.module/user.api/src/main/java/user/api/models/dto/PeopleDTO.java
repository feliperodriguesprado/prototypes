package user.api.models.dto;

import user.api.enums.PeopleType;

public class PeopleDTO {

    private long id;
    private String name;
    private PeopleType type;

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
        return type;
    }

    public void setType(PeopleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PeopleDTO{" + "id=" + id + ", name=" + name + ", type=" + type + '}';
    }

}
