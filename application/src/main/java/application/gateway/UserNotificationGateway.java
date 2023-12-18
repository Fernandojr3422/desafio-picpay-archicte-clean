package application.gateway;

import core.domain.Transaction;

public interface UserNotificationGateway {

    Boolean notification(Transaction transaction, String email);

}
