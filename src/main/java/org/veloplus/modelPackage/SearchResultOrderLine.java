package org.veloplus.modelPackage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SearchResultOrderLine {

    private LocalDate orderDate;
    private BigDecimal depositAmount;
    private Integer quantity;
    private BigDecimal linePrice;
    private BigDecimal discountPercent;
    private String bikeModel;
    private Integer bikeSizeId;
    private BigDecimal bikeUnitPrice;

    public SearchResultOrderLine() {
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    public Integer getBikeSizeId() {
        return bikeSizeId;
    }

    public void setBikeSizeId(Integer bikeSizeId) {
        this.bikeSizeId = bikeSizeId;
    }

    public BigDecimal getBikeUnitPrice() {
        return bikeUnitPrice;
    }

    public void setBikeUnitPrice(BigDecimal bikeUnitPrice) {
        this.bikeUnitPrice = bikeUnitPrice;
    }
}
