package org.veloplus.viewPackage;

import org.veloplus.modelPackage.RepairOrder;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RepairOrderTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "ID", "Client", "Description", "Depot", "Rendez-vous",
            "Devis", "Accepte", "Heures", "Statut", "Notes"
    };

    private ArrayList<RepairOrder> repairOrders;

    public RepairOrderTableModel() {
        repairOrders = new ArrayList<>();
    }

    public void setRepairOrders(ArrayList<RepairOrder> repairOrders) {
        this.repairOrders = repairOrders;
        fireTableDataChanged();
    }

    public RepairOrder getRepairOrderAt(int row) {
        return repairOrders.get(row);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return repairOrders.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        RepairOrder repairOrder = repairOrders.get(row);

        return switch (column) {
            case 0 -> repairOrder.getRepairId();
            case 1 -> repairOrder.getClient();
            case 2 -> repairOrder.getProblemDescription();
            case 3 -> repairOrder.getDepositDate();
            case 4 -> repairOrder.getAppointmentDate();
            case 5 -> repairOrder.getEstimateAmount();
            case 6 -> repairOrder.getAcceptedEstimate();
            case 7 -> repairOrder.getLaborHours();
            case 8 -> repairOrder.getStatus();
            case 9 -> repairOrder.getNotes();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int column) {
        Object value = null;

        if (getRowCount() > 0) {
            value = getValueAt(0, column);
        }

        if (value == null) {
            return Object.class;
        }

        return value.getClass();
    }
}
