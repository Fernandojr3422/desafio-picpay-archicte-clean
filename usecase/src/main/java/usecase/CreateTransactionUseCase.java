package usecase;

import core.domain.Transaction;

public interface CreateTransactionUseCase {

    Transaction create(Transaction transaction);

}
