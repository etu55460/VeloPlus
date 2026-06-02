package org.veloplus.modelPackage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RepairOrder {
    private Integer repairId;
    private Client client;
    private String problemDescription;
    private LocalDate depositDate;
    private LocalDate appointmentDate;
    private BigDecimal estimateAmount;
    private Boolean acceptedEstimate;
    private BigDecimal laborHours;
    private Status status;
    private String notes;

    public RepairOrder() {}

    public Integer getRepairId() { return repairId; }
    public void setRepairId(Integer repairId) { this.repairId = repairId; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public String getProblemDescription() { return problemDescription; }
    public void setProblemDescription(String problemDescription) { this.problemDescription = problemDescription; }

    public LocalDate getDepositDate() { return depositDate; }
    public void setDepositDate(LocalDate depositDate) { this.depositDate = depositDate; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public BigDecimal getEstimateAmount() { return estimateAmount; }
    public void setEstimateAmount(BigDecimal estimateAmount) { this.estimateAmount = estimateAmount; }

    public Boolean getAcceptedEstimate() { return acceptedEstimate; }
    public void setAcceptedEstimate(Boolean acceptedEstimate) { this.acceptedEstimate = acceptedEstimate; }

    public BigDecimal getLaborHours() { return laborHours; }
    public void setLaborHours(BigDecimal laborHours) { this.laborHours = laborHours; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}