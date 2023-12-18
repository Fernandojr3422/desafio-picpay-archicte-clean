package application.usecaseimpl;

import application.gateway.TransactionValidateGateway;
import core.domain.Transaction;
import core.exception.TransferException;
import core.exception.enums.ErrorCodeEnum;
import usecase.TransactionValidateUseCase;

public class TransactionValidateUseCaseImpl implements TransactionValidateUseCase {
    private TransactionValidateGateway transactionValidateGateway;

    public TransactionValidateUseCaseImpl(TransactionValidateGateway transactionValidateGateway) {
        this.transactionValidateGateway = transactionValidateGateway;
    }

    //método de caso de uso de validação de transação, o caso de uso esta validando a transação
    @Override
    public Boolean validate(Transaction transaction) throws TransferException {
        if (!transactionValidateGateway.validate(transaction)){
            throw new TransferException(ErrorCodeEnum.TR0004.getMessage(), ErrorCodeEnum.TR0004.getCode());
        }
        return true;
    }
}
