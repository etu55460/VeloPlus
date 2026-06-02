package org.veloplus.viewPackage;

import org.veloplus.modelPackage.SearchResultInvoice;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchInvoiceTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "ID facture", "Date facture", "Date paiement", "Montant total",
            "Payee", "Client", "Methode paiement"
    };

    private ArrayList<SearchResultInvoice> invoices;

    public SearchInvoiceTableModel(ArrayList<SearchResultInvoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        SearchResultInvoice invoice = invoices.get(row);

        return switch (column) {
            case 0 -> invoice.getInvoiceId();
            case 1 -> invoice.getInvoiceDate();
            case 2 -> invoice.getPaymentDate();
            case 3 -> invoice.getTotalAmount();
            case 4 -> invoice.getPaid();
            case 5 -> invoice.getClientName();
            case 6 -> invoice.getPaymentMethodName();
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
