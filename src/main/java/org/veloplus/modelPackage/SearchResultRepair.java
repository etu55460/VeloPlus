package org.veloplus.modelPackage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SearchResultRepair {

    private Integer repairId;
    private String clientName;
    private String problemDescription;
    private LocalDate depositDate;
    private BigDecimal estimateAmount;
    private Boolean acceptedEstimate;
    private String statusLabel;

    public SearchResultRepair() {
    }

    public Integer getRepairId() {
        return repairId;
    }

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public BigDecimal getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(BigDecimal estimateAmount) {
        this.estimateAmount = estimateAmount;
    }

    public Boolean getAcceptedEstimate() {
        return acceptedEstimate;
    }

    public void setAcceptedEstimate(Boolean acceptedEstimate) {
        this.acceptedEstimate = acceptedEstimate;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }
}
