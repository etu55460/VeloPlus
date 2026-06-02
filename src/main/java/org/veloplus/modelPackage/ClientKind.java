package org.veloplus.modelPackage;

public class ClientKind {
    private Integer kindId;
    private String label;

    public ClientKind() {}

    public Integer getKindId() { return kindId; }
    public void setKindId(Integer kindId) { this.kindId = kindId; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    @Override
    public String toString() {
        return label;
    }
}