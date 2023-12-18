package usecase;

import core.domain.User;
import core.domain.Wallet;

public interface FindWalletByTaxNumberUseCase {

    Wallet findByTaxNumber(String taxNumber);
}
