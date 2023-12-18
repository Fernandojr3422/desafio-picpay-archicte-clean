package application.usecaseimpl;

import application.gateway.CreateUserGateway;
import core.domain.TransactionPin;
import core.domain.User;
import core.domain.Wallet;
import core.exception.EmailException;
import core.exception.InternalServerErrorException;
import core.exception.TaxNumberException;
import core.exception.TransactionPinException;
import core.exception.enums.ErrorCodeEnum;
import usecase.*;

import java.math.BigDecimal;

/*
 *1) classe de implementação do caso de uso, da criação do usuário,
 * criação da carteira(wallet), criação do pin de transação(transactionPin).
 * 2) classe de implementação que chama o implments a Interface CreateUserUseCase;
 * 3) Analisando as regras de negocio, vamos criar esta usecaseImpl onde o usuario não poderá ter
 * o mesmo email, cpf e CNPJ.
*/

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private TaxNumberAvailableUseCase taxNumberAvailableUseCase;
    private EmailAvailableUseCase emailAvailableUseCase;

    //da camada de application do subPacote gateway
    private CreateUserGateway createUserGateway;


    public CreateUserUseCaseImpl(TaxNumberAvailableUseCase taxNumberAvailableUseCase,
                                 EmailAvailableUseCase emailAvailableUseCase,
                                 CreateUserGateway createUserGateway) {
        this.taxNumberAvailableUseCase = taxNumberAvailableUseCase;
        this.emailAvailableUseCase = emailAvailableUseCase;
        this.createUserGateway = createUserGateway;
    }

    /* 1)método de caso de uso de implementação da criação do usuário, criação da carteira do usuário,
     * criação do pin de transação
    **/
    @Override
    public void create(User user, String pin) throws TaxNumberException, EmailException, TransactionPinException,
            InternalServerErrorException {
        //uma regra de negocio do desafio, de verificação de cpf,cnpj,email se estão disponíveis
        //vamos verificar se esta disponivel ou não o cpf, cnpj
        if(!taxNumberAvailableUseCase.taxNumberAvailableNumber(user.getTaxNumber().getValue())){
            throw new TaxNumberException(ErrorCodeEnum.ON0002.getMessage(), ErrorCodeEnum.ON0002.getCode());
        }
        //verificar se o email esta disponivel
        if(!emailAvailableUseCase.emailAvailableEmail(user.getEmain())){
            throw new EmailException(ErrorCodeEnum.ON0003.getMessage(), ErrorCodeEnum.ON0003.getCode());
        }

        //criação do usuário, INserir aqui para não da erro e o usuário ficar sem carteira ou sem pin de
        //transação.
        //cria um usuario,cria a carteira, e transação pin

        if (!createUserGateway.create(user, new Wallet(BigDecimal.ZERO,user),
                new TransactionPin(user, pin))){
            throw new InternalServerErrorException(ErrorCodeEnum.ON0004.getMessage(),
                    ErrorCodeEnum.ON0004.getCode());
        }



    }
}
