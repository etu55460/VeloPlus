package org.veloplus.modelPackage;

public class Size {
    private Integer sizeId;
    private String label;

    public Size() {}

    public Integer getSizeId() { return sizeId; }
    public void setSizeId(Integer sizeId) { this.sizeId = sizeId; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    @Override
    public String toString() {
        return label;
    }
}