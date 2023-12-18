package application.gateway;

import core.domain.User;

/*1) Estas interfaces serão chamadas para a criação do usuário, e a implementação estará na camada maIS EXTERNA.
 *2) Ela e a porta de conexão com o modulo mais externo.
**/
public interface CreateUserGateway {
    User create(User user);
}
