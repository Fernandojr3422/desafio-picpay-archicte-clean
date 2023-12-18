package application.usecaseimpl;

import application.gateway.CreateWalletGateway;
import core.domain.Wallet;
import usecase.CreateWalletUseCase;

public class CreateWalletUseCaseImpl implements CreateWalletUseCase {

    private CreateWalletGateway createWalletGateway;

    public CreateWalletUseCaseImpl(CreateWalletGateway createWalletGateway) {
        this.createWalletGateway = createWalletGateway;
    }

    //Caso de uso de criação da carteira do usuario;
    @Override
    public void create(Wallet wallet) {
        createWalletGateway.create(wallet);
    }
}
