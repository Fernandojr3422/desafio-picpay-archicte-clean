package usecase;

import core.domain.Transaction;

public interface UserNotificationUseCase {
    //Vai receber uma transaction pq ela possui informações relacionadas a transação
    Boolean notificate(Transaction transaction, String email);
}
