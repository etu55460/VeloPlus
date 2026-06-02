package org.veloplus.modelPackage;

import java.math.BigDecimal;

public class Part {
    private Integer partId;
    private Integer reference;
    private String partName;
    private BigDecimal unitPrice;
    private Integer stockQuantity;
    private Supplier supplier;

    public Part() {}

    public Integer getPartId() { return partId; }
    public void setPartId(Integer partId) { this.partId = partId; }

    public Integer getReference() { return reference; }
    public void setReference(Integer reference) { this.reference = reference; }

    public String getPartName() { return partName; }
    public void setPartName(String partName) { this.partName = partName; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    @Override
    public String toString() {
        return partName;
    }
}