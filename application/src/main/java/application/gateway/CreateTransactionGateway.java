package application.gateway;

import core.domain.Transaction;

public interface CreateTransactionGateway {

    Transaction create(Transaction transaction);

}
