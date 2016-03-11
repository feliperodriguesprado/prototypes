package prototype.java.jsf.project2.models;

import java.io.Serializable;

public class PeopleModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long peopleId;

    public Long getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Long peopleId) {
        this.peopleId = peopleId;
    }

    @Override
    public String toString() {
        return "PeopleModel{" + "peopleId=" + peopleId + '}';
    }

}
