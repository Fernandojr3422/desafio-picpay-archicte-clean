package application.gateway;

import core.domain.Transaction;

public interface TransferUserCaseGateway {

    Boolean transfer(Transaction transaction);

}
