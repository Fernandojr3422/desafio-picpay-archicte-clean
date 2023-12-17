package usecase;

import core.domain.User;

public interface FindUserByTaxNumberUseCase {

    User findByTaxNumber(String taxNumber);
}
