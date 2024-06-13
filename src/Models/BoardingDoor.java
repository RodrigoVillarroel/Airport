package Models;

import java.text.MessageFormat;

public class BoardingDoor {
    private int code;
    private boolean status;

    public BoardingDoor(int code, boolean status) {
        setCode(code);
        setStatus(status);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MessageFormat.format("BoardingDoor'{'code={0}, status={1}'}'", getCode(), isStatus());
    }
}
