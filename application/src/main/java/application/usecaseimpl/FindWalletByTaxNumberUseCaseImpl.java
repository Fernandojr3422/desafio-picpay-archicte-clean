package application.usecaseimpl;

import application.gateway.FindWalletByTaxNumberGateway;
import core.domain.Wallet;
import core.exception.NotFoundException;
import core.exception.enums.ErrorCodeEnum;
import usecase.FindWalletByTaxNumberUseCase;

public class FindWalletByTaxNumberUseCaseImpl implements FindWalletByTaxNumberUseCase {

    private FindWalletByTaxNumberGateway findWalletByTaxNumberGateway;

    public FindWalletByTaxNumberUseCaseImpl(FindWalletByTaxNumberGateway findWalletByTaxNumberGateway) {
        this.findWalletByTaxNumberGateway = findWalletByTaxNumberGateway;
    }

    //Método de caso de uso que vai buscar uma carteira por CPF ou CNPJ;
    @Override
    public Wallet findByTaxNumber(String taxNumber) throws NotFoundException {
        var wallet = findWalletByTaxNumberGateway.findByTaxNumber(taxNumber);
        if(wallet == null){
            throw new NotFoundException(ErrorCodeEnum.WA0001.getMessage(), ErrorCodeEnum.WA0001.getCode());
        }
        return wallet;

    }
}
