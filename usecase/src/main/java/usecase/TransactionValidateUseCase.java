package usecase;

import core.domain.Transaction;
import core.exception.TransferException;

public interface TransactionValidateUseCase {
    Boolean validate(Transaction transaction) throws TransferException;
}
