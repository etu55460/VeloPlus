package org.veloplus.controllerPackage;

import org.veloplus.businessPackage.InvoiceCalculator;
import org.veloplus.exceptionPackage.BusinessException;

import java.math.BigDecimal;

public class BusinessTaskController {

    private InvoiceCalculator invoiceCalculator;

    public BusinessTaskController() {
        invoiceCalculator = new InvoiceCalculator();
    }

    public BigDecimal calculateRepairTotal(BigDecimal estimateAmount, BigDecimal laborHours, BigDecimal hourlyRate)
            throws BusinessException {
        return invoiceCalculator.calculateRepairTotal(estimateAmount, laborHours, hourlyRate);
    }
}
