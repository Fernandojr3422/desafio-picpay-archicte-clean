package application.gateway;

import core.domain.Transaction;

public interface TransactionValidateGateway {
    Boolean validate(Transaction transaction);
}
