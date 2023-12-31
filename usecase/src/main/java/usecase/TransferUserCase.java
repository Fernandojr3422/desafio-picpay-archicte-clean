package usecase;

import core.domain.Transaction;
import core.exception.InternalServerErrorException;
import core.exception.NotFoundException;
import core.exception.NotificationException;
import core.exception.TransferException;

import java.math.BigDecimal;

public interface TransferUserCase {
    Boolean transfer(String fromTaxNumber, String toTaxNumber, BigDecimal value) throws InternalServerErrorException, TransferException, NotFoundException, NotificationException;
}
