package men.brakh;

public class Message {
    private String text;
    private User user;
    private MessageStatus status;

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Message(String text, User user, MessageStatus status) {
        this.text = text;
        this.user = user;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", user == null ? "" : user.getName(), text);
    }
}
