package core.domain;

import core.domain.enums.UserTypeEnum;
import core.exception.TransferException;
import core.exception.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * 1) Carteira do usuário
 * 2) retirou o setBalance
 * 3) retirou o setCreatedAt
 * 4) A regra de negocio fica aqui dentro da camada de entities: Um logista não pode transferir e sim so receber
*/
public class Wallet {

    private Long id;
    private BigDecimal balance;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public Wallet(Long id, BigDecimal balance, User user, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Wallet(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    //pode ser o exemplo do desafio.
    //aquele conceito de class não anemicas
    //A regra de negocio fica aqui dentro da camada de entities
    //metodo do valor recebido
    //AS DUAS VALIDAÇÕES DO MÉTODO DE TRANSFERENCIA! DO DESAFIO,
    //DICA POSSO USAR ELA PARA MEU EXEMPLO
    public void receiveValue(BigDecimal value){
        this.balance.add(value);
    }

    //Para a regra onde se o usuario for um logista, ele não pode transferir
    public void transf(BigDecimal value) throws TransferException {
        if (this.user.getType() == UserTypeEnum.SHOPKEEPER){
            throw new TransferException(ErrorCodeEnum.TR0001.getMessage(), ErrorCodeEnum.TR0001.getCode());
        }

        //se o valor for menor que zero , não sera possivel transferir
        if(this.balance.compareTo(value) < 0){
            throw new TransferException(ErrorCodeEnum.TR0002.getMessage(), ErrorCodeEnum.TR0002.getCode());
        }

        this.balance.subtract(value);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
