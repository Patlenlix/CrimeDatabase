package se.iths.crimedatabase.messaging;

import java.util.Date;

public class CustomMessage {
    private String messageID;
    private String message;
    private Date messageDate;

    public CustomMessage(String messageID, String message, Date messageDate) {
        this.messageID = messageID;
        this.message = message;
        this.messageDate = messageDate;
    }

    public CustomMessage() {}

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "messageID='" + messageID + '\'' +
                ", message='" + message + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
