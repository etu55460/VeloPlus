package org.veloplus.viewPackage;

import org.veloplus.modelPackage.SearchResultOrderLine;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchOrderLineTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "Date commande", "Acompte", "Quantite", "Prix ligne",
            "Remise %", "Modele velo", "Taille ID", "Prix velo"
    };

    private ArrayList<SearchResultOrderLine> orderLines;

    public SearchOrderLineTableModel(ArrayList<SearchResultOrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return orderLines.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        SearchResultOrderLine orderLine = orderLines.get(row);

        return switch (column) {
            case 0 -> orderLine.getOrderDate();
            case 1 -> orderLine.getDepositAmount();
            case 2 -> orderLine.getQuantity();
            case 3 -> orderLine.getLinePrice();
            case 4 -> orderLine.getDiscountPercent();
            case 5 -> orderLine.getBikeModel();
            case 6 -> orderLine.getBikeSizeId();
            case 7 -> orderLine.getBikeUnitPrice();
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
