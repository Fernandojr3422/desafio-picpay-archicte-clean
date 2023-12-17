package usecase;

import core.domain.Transaction;

public interface TransferUserCase {
    Boolean transfer(Transaction transaction);
}
