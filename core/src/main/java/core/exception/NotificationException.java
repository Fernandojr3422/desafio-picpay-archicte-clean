package core.exception;
//exceção de numero de identificação fiscal
public class NotificationException extends Exception{

    private String code;

    public NotificationException(String message, String code) {
        super(message);
        this.code = code;
    }
}
