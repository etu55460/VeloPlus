package org.veloplus.modelPackage;

public class PaymentMethod {
    private Integer paymentMethodId;
    private String paymentMethodName;

    public PaymentMethod() {}

    public Integer getPaymentMethodId() { return paymentMethodId; }
    public void setPaymentMethodId(Integer paymentMethodId) { this.paymentMethodId = paymentMethodId; }

    public String getPaymentMethodName() { return paymentMethodName; }
    public void setPaymentMethodName(String paymentMethodName) { this.paymentMethodName = paymentMethodName; }

    @Override
    public String toString() {
        return paymentMethodName;
    }
}