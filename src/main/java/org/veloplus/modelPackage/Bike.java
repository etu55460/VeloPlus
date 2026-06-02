package org.veloplus.modelPackage;

import java.math.BigDecimal;

public class Bike {
    private Integer bikeId;
    private String model;
    private Size size;
    private Color color;
    private BigDecimal unitPrice;
    private Boolean used;
    private String conditionNote;
    private BigDecimal purchasePrice;
    private String description;

    public Bike() {}

    public Integer getBikeId() { return bikeId; }
    public void setBikeId(Integer bikeId) { this.bikeId = bikeId; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Size getSize() { return size; }
    public void setSize(Size size) { this.size = size; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public Boolean getUsed() { return used; }
    public void setUsed(Boolean used) { this.used = used; }

    public String getConditionNote() { return conditionNote; }
    public void setConditionNote(String conditionNote) { this.conditionNote = conditionNote; }

    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return model;
    }
}