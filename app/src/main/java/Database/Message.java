package Database;

import android.provider.BaseColumns;

public final class Message {

    private int messageID;
    private String un, subject, message;

    public Message() {}

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getUn() {
        return un;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUn(String un) {
        this.un = un;
    }


    public static class MessageData implements BaseColumns {
        public static final String TABLE_NAME = "Messages";
        public static final String COLUMN_NAME_USERNAME = "Username";
        public static final String COLUMN_NAME_SUBJECT = "Subject";
        public static final String COLUMN_NAME_MESSAGE = "Message";
    }
}
