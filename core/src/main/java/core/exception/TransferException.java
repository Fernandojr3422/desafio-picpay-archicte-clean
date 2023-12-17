package core.exception;

// extends Throwable mudou para o Exception
public class TransferException extends Exception {

    private String code;

    public TransferException(String message, String code) {
        super(message);
        this.code = code;
    }


}
