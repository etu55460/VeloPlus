package org.veloplus.viewPackage;

import org.veloplus.modelPackage.SearchResultRepair;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchRepairTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "ID reparation", "Client", "Description", "Date depot", "Devis", "Accepte", "Statut"
    };

    private ArrayList<SearchResultRepair> repairs;

    public SearchRepairTableModel(ArrayList<SearchResultRepair> repairs) {
        this.repairs = repairs;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return repairs.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        SearchResultRepair repair = repairs.get(row);

        return switch (column) {
            case 0 -> repair.getRepairId();
            case 1 -> repair.getClientName();
            case 2 -> repair.getProblemDescription();
            case 3 -> repair.getDepositDate();
            case 4 -> repair.getEstimateAmount();
            case 5 -> repair.getAcceptedEstimate();
            case 6 -> repair.getStatusLabel();
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
