package application.usecaseimpl;

import application.gateway.TransferUserCaseGateway;
import core.domain.Transaction;
import core.domain.Wallet;
import core.exception.InternalServerErrorException;
import core.exception.NotFoundException;
import core.exception.NotificationException;
import core.exception.TransferException;
import core.exception.enums.ErrorCodeEnum;
import usecase.*;

import java.math.BigDecimal;
/*
 * 1) Na regra do Picpay precisamos consultar valor, uma Wallet(carteira)
 * se usuario e normal ou logista e se pode enviar dinheiro;
 * 2) e validar se user tem saldo
 * 3) Precisamos criar um serviço de autorizador externo;
 * 4) Precisaremos criar uma transação;
 * 5) Vamos precisar subtrair o valor da carteira de quem esta enviando dinheiro ,
 * 6) e adicionar o valor de quem esta recebendo.
 * 7) O usuário precisa ser notificado quando a ocorrência acontece
**/
public class TransferUserCaseImpl implements TransferUserCase {

    private TransferUserCaseGateway transferUserCaseGateway;
    private FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase;
    private TransactionValidateUseCase transactionValidateUseCase;
    private CreateTransactionUseCase createTransactionUseCase;
    private UserNotificationUseCase userNotificationUseCase;

    public TransferUserCaseImpl(TransferUserCaseGateway transferUserCaseGateway,
                                FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase,
                                TransactionValidateUseCase transactionValidateUseCase,
                                CreateTransactionUseCase createTransactionUseCase,
                                UserNotificationUseCase userNotificationUseCase) {
        this.transferUserCaseGateway = transferUserCaseGateway;
        this.findWalletByTaxNumberUseCase = findWalletByTaxNumberUseCase;
        this.transactionValidateUseCase = transactionValidateUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
        this.userNotificationUseCase = userNotificationUseCase;
    }

    //Método de caso de uso de transferencia
    @Override
    public Boolean transfer(String fromTaxNumber, String toTaxNumber, BigDecimal value)
            throws InternalServerErrorException, TransferException, NotFoundException, NotificationException {
        Wallet from = findWalletByTaxNumberUseCase.findByTaxNumber(fromTaxNumber);
        Wallet to = findWalletByTaxNumberUseCase.findByTaxNumber(toTaxNumber);

        from.transf(value); //de quem tirado
        to.receiveTransf(value); // para quem foi adicionado

        //criação da transação
        var transaction = createTransactionUseCase.create(new Transaction(from, to, value));
        //validação da transação
        transactionValidateUseCase.validate(transaction);

        if(!transferUserCaseGateway.transfer(transaction)){
            throw new InternalServerErrorException(ErrorCodeEnum.TR0003.getMessage(),
                    ErrorCodeEnum.TR0003.getCode());
        }

        //estamos notificando o usuario que a transferencia aconteceu
        if (!userNotificationUseCase.notificate(transaction, to.getUser().getEmain())){
            throw new NotificationException(ErrorCodeEnum.NO0001.getMessage(), ErrorCodeEnum.NO0001.getCode());
        }

        return true;
    }
}
