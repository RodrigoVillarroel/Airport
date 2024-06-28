package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;

public class BoardingDoor {
    @JsonProperty("code")
    private int code;
    @JsonProperty("status")
    private boolean status;

    public BoardingDoor() {
    }

    public BoardingDoor(int code, boolean status) {
        setCode(code);
        setStatus(status);
    }

    // region Getters & Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    // endregion

    @Override
    public String toString() {
        return MessageFormat.format("BoardingDoor'{'code={0}, status={1}'}'", getCode(), getStatus());
    }
}
