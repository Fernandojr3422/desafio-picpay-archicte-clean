package usecase;

import core.domain.User;

//temos aqui o princio do SOLID ( Responsabily principle que é o principio da responsabilidade unica
//Então vamos criar esse método que vai ter a unica responsabilidade que e criar um usuário
public interface CreateUserUseCase {

    void create(User user);

}
