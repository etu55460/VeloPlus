package org.veloplus.businessPackage;

import org.veloplus.exceptionPackage.BusinessException;
import org.veloplus.exceptionPackage.ValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvoiceCalculator {

    public BigDecimal calculateRepairTotal(BigDecimal estimateAmount, BigDecimal laborHours, BigDecimal hourlyRate)
            throws BusinessException {

        if (estimateAmount == null) {
            throw new ValidationException("Le montant du devis est obligatoire.");
        }

        if (laborHours == null) {
            laborHours = BigDecimal.ZERO;
        }

        if (hourlyRate == null) {
            throw new ValidationException("Le tarif horaire est obligatoire.");
        }

        if (estimateAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Le montant du devis ne peut pas être négatif.");
        }

        if (laborHours.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Le nombre d'heures ne peut pas être négatif.");
        }

        if (hourlyRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Le tarif horaire ne peut pas être négatif.");
        }

        BigDecimal laborAmount = laborHours.multiply(hourlyRate);
        BigDecimal total = estimateAmount.add(laborAmount);

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
