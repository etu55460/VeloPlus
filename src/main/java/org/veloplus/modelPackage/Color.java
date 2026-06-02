package org.veloplus.modelPackage;

public class Color {
    private Integer colorId;
    private String colorName;

    public Color() {}

    public Integer getColorId() { return colorId; }
    public void setColorId(Integer colorId) { this.colorId = colorId; }

    public String getColorName() { return colorName; }
    public void setColorName(String colorName) { this.colorName = colorName; }

    @Override
    public String toString() {
        return colorName;
    }
}