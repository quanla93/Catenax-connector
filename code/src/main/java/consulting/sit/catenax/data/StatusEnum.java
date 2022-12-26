package consulting.sit.catenax.data;

public enum StatusEnum {
    PENDING("pending"),
    NOT_RECEIVED("not received"),
    RECEIVED_SUCCESS("received success"),
    RECEIVED_FAILURE("received failure");

    private final String name;

    StatusEnum(String name) {
        this.name = name;
    }
}
