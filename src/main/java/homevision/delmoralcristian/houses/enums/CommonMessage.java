package homevision.delmoralcristian.houses.enums;

public enum CommonMessage {

    HOUSE_NOT_FOUND("House not found - Id: '%s'"),
    USER_NOT_FOUND("Username '%s' not found"),
    API_CALL_ERROR("Error while calling house API"),
    INVALID_PAYLOAD_FORMAT("Invalid payload format.");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
