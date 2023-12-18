package usecase;

import core.domain.User;
import core.domain.Wallet;
import core.exception.NotFoundException;

public interface FindWalletByTaxNumberUseCase {
    Wallet findByTaxNumber(String taxNumber) throws NotFoundException;
}
