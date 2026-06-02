package org.veloplus.businessPackage;

import org.junit.Assert;
import org.junit.Test;
import org.veloplus.exceptionPackage.BusinessException;
import org.veloplus.exceptionPackage.ValidationException;

import java.math.BigDecimal;

public class InvoiceCalculatorTest {

    @Test
    public void calculateRepairTotalWithEstimateAndLaborReturnsTotal() throws BusinessException {
        // Arrange
        InvoiceCalculator calculator = new InvoiceCalculator();
        BigDecimal estimateAmount = new BigDecimal("100.00");
        BigDecimal laborHours = new BigDecimal("2.50");
        BigDecimal hourlyRate = new BigDecimal("40.00");

        // Act
        BigDecimal total = calculator.calculateRepairTotal(estimateAmount, laborHours, hourlyRate);

        // Assert
        Assert.assertEquals(new BigDecimal("200.00"), total);
    }

    @Test
    public void calculateRepairTotalWithNullLaborHoursUsesZero() throws BusinessException {
        // Arrange
        InvoiceCalculator calculator = new InvoiceCalculator();

        // Act
        BigDecimal total = calculator.calculateRepairTotal(new BigDecimal("25.00"), null, new BigDecimal("35.00"));

        // Assert
        Assert.assertEquals(new BigDecimal("25.00"), total);
    }

    @Test
    public void calculateRepairTotalRoundsToTwoDecimals() throws BusinessException {
        // Arrange
        InvoiceCalculator calculator = new InvoiceCalculator();

        // Act
        BigDecimal total = calculator.calculateRepairTotal(
                new BigDecimal("10.005"),
                BigDecimal.ZERO,
                new BigDecimal("25.00")
        );

        // Assert
        Assert.assertEquals(new BigDecimal("10.01"), total);
    }

    @Test(expected = ValidationException.class)
    public void calculateRepairTotalWithNullHourlyRateThrowsException() throws BusinessException {
        // Arrange
        InvoiceCalculator calculator = new InvoiceCalculator();

        // Act
        calculator.calculateRepairTotal(BigDecimal.ZERO, BigDecimal.ZERO, null);
    }

    @Test(expected = ValidationException.class)
    public void calculateRepairTotalWithNullEstimateThrowsException() throws BusinessException {
        // Arrange
        InvoiceCalculator calculator = new InvoiceCalculator();

        // Act
        calculator.calculateRepairTotal(null, BigDecimal.ZERO, BigDecimal.ONE);
    }

    @Test(expected = ValidationException.class)
    public void calculateRepairTotalWithNegativeEstimateThrowsException() throws BusinessException {
        // Arrange
        InvoiceCalculator calculator = new InvoiceCalculator();

        // Act
        calculator.calculateRepairTotal(new BigDecimal("-0.01"), BigDecimal.ZERO, BigDecimal.ONE);
    }
}
