package application.usecaseimpl;

import application.gateway.CreateTransactionGateway;
import core.domain.Transaction;
import core.exception.TransferException;
import core.exception.enums.ErrorCodeEnum;
import usecase.CreateTransactionUseCase;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {
    private CreateTransactionGateway createTransactionGateway;

    public CreateTransactionUseCaseImpl(CreateTransactionGateway createTransactionGateway) {
        this.createTransactionGateway = createTransactionGateway;
    }

    //método de caso de uso de creação de transação
    @Override
    public Transaction create(Transaction transaction) throws TransferException {
        var transactionSaved = createTransactionGateway.create(transaction);
        if(transactionSaved == null){
            throw new TransferException(ErrorCodeEnum.TR0003.getMessage(), ErrorCodeEnum.TR0003.getCode());
        }
        return transactionSaved;
    }
}
