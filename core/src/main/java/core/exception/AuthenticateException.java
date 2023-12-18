package core.exception;
//exceção de numero de identificação fiscal
public class AuthenticateException extends Exception{

    private String code;

    public AuthenticateException(String message, String code) {
        super(message);
        this.code = code;
    }
}
