package org.veloplus.viewPackage;

import org.veloplus.controllerPackage.ClientController;
import org.veloplus.controllerPackage.SearchController;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.modelPackage.Client;
import org.veloplus.modelPackage.SearchResultInvoice;
import org.veloplus.modelPackage.SearchResultOrderLine;
import org.veloplus.modelPackage.SearchResultRepair;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class SearchPanel extends JPanel {

    public enum SearchMode {
        REPAIRS_BY_CLIENT,
        BIKE_ORDERS_BETWEEN_DATES,
        INVOICES_BY_PAID_STATUS
    }

    private SearchController searchController;
    private ClientController clientController;
    private SearchMode searchMode;

    private JComboBox<Client> clientComboBox;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JCheckBox paidCheckBox;

    private JTable resultTable;

    public SearchPanel(SearchMode searchMode) throws ApplicationException {
        this.searchMode = searchMode;
        searchController = new SearchController();
        clientController = new ClientController();

        this.setLayout(new BorderLayout());

        createSearchFields();
        createResultTable();
    }

    private void createSearchFields() {
        if (searchMode == SearchMode.REPAIRS_BY_CLIENT) {
            this.add(createRepairSearchPanel(), BorderLayout.NORTH);
            loadClients();
        } else if (searchMode == SearchMode.BIKE_ORDERS_BETWEEN_DATES) {
            this.add(createBikeOrderSearchPanel(), BorderLayout.NORTH);
        } else if (searchMode == SearchMode.INVOICES_BY_PAID_STATUS) {
            this.add(createInvoiceSearchPanel(), BorderLayout.NORTH);
        }
    }

    private JPanel createRepairSearchPanel() {
        JPanel repairSearchPanel = new JPanel(new FlowLayout());
        clientComboBox = new JComboBox<>();
        JButton repairsByClientButton = new JButton("Reparations du client");
        repairsByClientButton.addActionListener(event -> searchRepairsByClient());

        repairSearchPanel.add(new JLabel("Client :"));
        repairSearchPanel.add(clientComboBox);
        repairSearchPanel.add(repairsByClientButton);

        return repairSearchPanel;
    }

    private JPanel createBikeOrderSearchPanel() {
        JPanel bikeOrderSearchPanel = new JPanel(new FlowLayout());
        startDateSpinner = createDateSpinner();
        endDateSpinner = createDateSpinner();
        JButton bikeOrdersButton = new JButton("Commandes velos entre dates");
        bikeOrdersButton.addActionListener(event -> searchBikeOrdersBetweenDates());

        bikeOrderSearchPanel.add(new JLabel("Date debut :"));
        bikeOrderSearchPanel.add(startDateSpinner);
        bikeOrderSearchPanel.add(new JLabel("Date fin :"));
        bikeOrderSearchPanel.add(endDateSpinner);
        bikeOrderSearchPanel.add(bikeOrdersButton);

        return bikeOrderSearchPanel;
    }

    private JPanel createInvoiceSearchPanel() {
        JPanel invoiceSearchPanel = new JPanel(new FlowLayout());
        paidCheckBox = new JCheckBox("Payee");
        JButton invoicesButton = new JButton("Rechercher factures");
        invoicesButton.addActionListener(event -> searchInvoicesByPaidStatus());

        invoiceSearchPanel.add(paidCheckBox);
        invoiceSearchPanel.add(invoicesButton);

        return invoiceSearchPanel;
    }

    private JSpinner createDateSpinner() {
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd"));
        return spinner;
    }

    private void createResultTable() {
        resultTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultTable);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void loadClients() {
        try {
            ArrayList<Client> clients = clientController.getAllClients();

            for (Client client : clients) {
                clientComboBox.addItem(client);
            }

        } catch (Exception exception) {
            showError(exception);
        }
    }

    private void searchRepairsByClient() {
        try {
            Client selectedClient = (Client) clientComboBox.getSelectedItem();

            if (selectedClient == null) {
                JOptionPane.showMessageDialog(this, "Veuillez selectionner un client.");
                return;
            }

            ArrayList<SearchResultRepair> results =
                    searchController.findRepairsByClient(selectedClient.getClientId());

            resultTable.setModel(new SearchRepairTableModel(results));

        } catch (Exception exception) {
            showError(exception);
        }
    }

    private void searchBikeOrdersBetweenDates() {
        try {
            LocalDate startDate = getLocalDate(startDateSpinner);
            LocalDate endDate = getLocalDate(endDateSpinner);

            ArrayList<SearchResultOrderLine> results =
                    searchController.findBikeOrderLinesBetweenDates(startDate, endDate);

            resultTable.setModel(new SearchOrderLineTableModel(results));

        } catch (Exception exception) {
            showError(exception);
        }
    }

    private LocalDate getLocalDate(JSpinner spinner) {
        Date date = (Date) spinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private void searchInvoicesByPaidStatus() {
        try {
            boolean paid = paidCheckBox.isSelected();

            ArrayList<SearchResultInvoice> results =
                    searchController.findInvoicesByPaidStatus(paid);

            resultTable.setModel(new SearchInvoiceTableModel(results));

        } catch (Exception exception) {
            showError(exception);
        }
    }

    private void showError(Exception exception) {
        JOptionPane.showMessageDialog(
                this,
                exception.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
