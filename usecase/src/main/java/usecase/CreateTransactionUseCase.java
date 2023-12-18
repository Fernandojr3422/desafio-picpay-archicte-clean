package usecase;

import core.domain.Transaction;
import core.exception.TransferException;

public interface CreateTransactionUseCase {

    Transaction create(Transaction transaction) throws TransferException;

}
