package usecase;

import core.domain.Transaction;

public interface TransactionValidateUseCase {
    Boolean validate(Transaction transaction);
}
