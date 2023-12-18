package application.usecaseimpl;

import application.gateway.UserAuthenticateGateway;
import core.exception.AuthenticateException;
import core.exception.enums.ErrorCodeEnum;
import usecase.UserAuthenticateUseCase;

public class UserAuthenticateUseCaseImpl implements UserAuthenticateUseCase {

    private UserAuthenticateGateway userAuthenticateGateway;

    public UserAuthenticateUseCaseImpl(UserAuthenticateGateway userAuthenticateGateway) {
        this.userAuthenticateGateway = userAuthenticateGateway;
    }

    //método de caso de uso de autenticação do usuário
    @Override
    public Boolean authenticate(String username, String password) throws AuthenticateException {
        if (!userAuthenticateGateway.authenticate(username, password)){
            throw new AuthenticateException(ErrorCodeEnum.ATH0001.getMessage(),
                    ErrorCodeEnum.ATH0001.getCode());
        }
        return true;
    }
}
