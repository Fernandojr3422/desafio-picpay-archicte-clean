package usecase;

import core.domain.User;
import core.exception.EmailException;
import core.exception.InternalServerErrorException;
import core.exception.TaxNumberException;
import core.exception.TransactionPinException;

//temos aqui o princio do SOLID ( Responsabily principle que é o principio da responsabilidade unica
//Então vamos criar esse método que vai ter a unica responsabilidade que e criar um usuário
public interface CreateUserUseCase {

    void create(User user, String pin) throws TaxNumberException, EmailException, TransactionPinException, InternalServerErrorException;

}
