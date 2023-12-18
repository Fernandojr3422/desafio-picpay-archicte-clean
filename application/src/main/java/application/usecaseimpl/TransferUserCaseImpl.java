package application.usecaseimpl;

import application.gateway.TransferUserCaseGateway;
import core.domain.Transaction;
import core.domain.Wallet;
import core.exception.InternalServerErrorException;
import core.exception.TransferException;
import core.exception.enums.ErrorCodeEnum;
import usecase.CreateTransactionUseCase;
import usecase.FindWalletByTaxNumberUseCase;
import usecase.TransactionValidateUseCase;
import usecase.TransferUserCase;

import java.math.BigDecimal;
/*
 * 1) Na regra do Picpay precisamos consultar valor, uma Wallet(carteira)
 * se usuario e normal ou logista e se pode enviar dinheiro;
 * 2) e validar se user tem saldo
 * 3) Precisamos criar um serviço de autorizador externo;
 * 4) Precisaremos criar uma transação;
 * 5) Vamos precisar subtrair o valor da carteira de quem esta enviando dinheiro ,
 * 6) e adicionar o valor de quem esta recebendo
**/
public class TransferUserCaseImpl implements TransferUserCase {

    private TransferUserCaseGateway transferUserCaseGateway;
    private FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase;
    private TransactionValidateUseCase transactionValidateUseCase;
    private CreateTransactionUseCase createTransactionUseCase;

    public TransferUserCaseImpl(TransferUserCaseGateway transferUserCaseGateway,
                                FindWalletByTaxNumberUseCase findWalletByTaxNumberUseCase,
                                TransactionValidateUseCase transactionValidateUseCase,
                                CreateTransactionUseCase createTransactionUseCase) {
        this.transferUserCaseGateway = transferUserCaseGateway;
        this.findWalletByTaxNumberUseCase = findWalletByTaxNumberUseCase;
        this.transactionValidateUseCase = transactionValidateUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
    }

    //Método de caso de uso de transferencia
    @Override
    public Boolean transfer(String fromTaxNumber, String toTaxNumber, BigDecimal value) throws InternalServerErrorException, TransferException {
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
        return true;
    }
}
