package org.veloplus.modelPackage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {
    private Integer invoiceId;
    private Client client;
    private SaleOrder saleOrder;
    private RepairOrder repairOrder;
    private LocalDate invoiceDate;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;
    private BigDecimal totalAmount;
    private Boolean paid;

    public Invoice() {}

    public Integer getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Integer invoiceId) { this.invoiceId = invoiceId; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public SaleOrder getSaleOrder() { return saleOrder; }
    public void setSaleOrder(SaleOrder saleOrder) { this.saleOrder = saleOrder; }

    public RepairOrder getRepairOrder() { return repairOrder; }
    public void setRepairOrder(RepairOrder repairOrder) { this.repairOrder = repairOrder; }

    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public Boolean getPaid() { return paid; }
    public void setPaid(Boolean paid) { this.paid = paid; }
}