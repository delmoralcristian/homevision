package homevision.delmoralcristian.houses.enums;

public enum CommonMessage {

    USER_NOT_FOUND("Username '%s' not found"),
    API_CALL_ERROR("Error while calling house API"),
    INVALID_PAYLOAD_FORMAT("Invalid payload format."),
    PHOTO_DOWNLOAD_ERROR("Error while downloading house photos");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
