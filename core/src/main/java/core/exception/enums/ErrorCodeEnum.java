package core.exception.enums;

//enum com codigo de erro
//Vai possuir as mensagens de exceção
public enum ErrorCodeEnum {

    ON0001("Tax Number inválido", "ON-0001"),
    ON0002("Tax Number indisponível", "ON-0002"),
    ON0003("Email indisponível", "ON-0003"),
    TR0001("Usuário lojista não tem a função de transferência disponível", "TR-0001"),
    TR0002("Saldo Indisponível", "TR-0002"),
    TRP0001("PIN inválido", "TRP-0001");
    private String message;
    private String code;

    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



}
