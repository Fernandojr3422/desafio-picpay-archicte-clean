package core.exception;
//exceção de numero de identificação fiscal
public class InternalServerErrorException extends Exception{

    private String code;

    public InternalServerErrorException(String message, String code) {
        super(message);
        this.code = code;
    }
}
