package application.gateway;

import core.domain.TransactionPin;
import core.domain.User;
import core.domain.Wallet;

/*1) Estas interfaces serão chamadas para a criação do usuário, e a implementação estará na camada maIS EXTERNA.
 *2) Ela e a porta de conexão com o modulo mais externo.
**/
public interface CreateUserGateway {
    Boolean create(User user, Wallet wallet, TransactionPin transactionPin);
}
