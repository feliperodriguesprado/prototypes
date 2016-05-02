package project1.user.api.enums;

public enum PeopleType {

    USER(1, "user"),
    CUSTOMER(2, "customer");

    private final int code;
    private final String description;

    private PeopleType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PeopleType parse (int code) {
        
        PeopleType peopleType = null;
        
        for (PeopleType value : PeopleType.values()) {
            if (value.getCode() == code) {
                peopleType = value;
                break;
            }
        }
        
        return peopleType;
    }
    
    @Override
    public String toString() {
        return "PeopleType {" + "code=" + code + ", description=" + description + '}';
    }

}
