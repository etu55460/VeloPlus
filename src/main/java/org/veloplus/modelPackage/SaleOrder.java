package org.veloplus.modelPackage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleOrder {
    private Integer orderId;
    private Client client;
    private LocalDate orderDate;
    private Status status;
    private BigDecimal depositAmount;
    private Boolean professionalOrder;
    private String notes;

    public SaleOrder() {}

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public BigDecimal getDepositAmount() { return depositAmount; }
    public void setDepositAmount(BigDecimal depositAmount) { this.depositAmount = depositAmount; }

    public Boolean getProfessionalOrder() { return professionalOrder; }
    public void setProfessionalOrder(Boolean professionalOrder) { this.professionalOrder = professionalOrder; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}