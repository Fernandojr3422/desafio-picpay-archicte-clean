package application.usecaseimpl;

import application.gateway.UserNotificationGateway;
import core.domain.Transaction;
import usecase.UserNotificationUseCase;

public class UserNotificationUseCaseImpl implements UserNotificationUseCase {

    private UserNotificationGateway userNotificationGateway;

    public UserNotificationUseCaseImpl(UserNotificationGateway userNotificationGateway) {
        this.userNotificationGateway = userNotificationGateway;
    }

    //Método de caso de uso de Notificação de usuário
    @Override
    public Boolean notificate(Transaction transaction, String email) {
        return userNotificationGateway.notification(transaction,email);
    }
}
