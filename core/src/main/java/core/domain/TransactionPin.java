package core.domain;

import core.exception.TaxNumberException;
import core.exception.TransactionPinException;
import core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;

//CLASS QUE DISPONIBILIZAR O CODIGO DE TRANSAÇÃO
//retirado o setCreatedAt
public class TransactionPin {

    private Long id;
    private User user;
    private String pin;
    private Integer attempt; //e a qtd de tentativas qdo erra o pin
    private Boolean blocked; //para saber se a senha esta bloqueada
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public TransactionPin(Long id, User user, String pin, Integer attempt, Boolean blocked,
                          LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.user = user;
        this.pin = pin;
        this.attempt = attempt;
        this.blocked = blocked;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public TransactionPin(User user, String pin) throws TransactionPinException {
        setPin(pin);
        this.pin = pin;
        this.attempt = 3; //regra de negocio
        this.blocked = false; //regra de negocio
        this.createdAt = LocalDateTime.now();
    }

    public TransactionPin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) throws TransactionPinException {
        pinIsValid(pin);
        this.pin = pin;
    }

    //validação do pin, se ele for menor que 8, e possuir data de aniversário por exemplo
    //neste caso como estamos estourando a exceção dentro do proprio metodo nao precisamos retorna nada,
    //logo mudamos private Boolean para void
    private void pinIsValid(String pin) throws TransactionPinException{
        if(pin.length() != 8){
            throw new TransactionPinException(ErrorCodeEnum.TRP0001.getMessage(), ErrorCodeEnum.TRP0001.getCode());
        }
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
