package core.exception;
//exceção de numero de identificação fiscal
public class BadRequestException extends Exception{

    private String code;

    public BadRequestException(String message, String code) {
        super(message);
        this.code = code;
    }
}
