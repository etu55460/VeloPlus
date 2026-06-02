package org.veloplus.viewPackage;

import org.veloplus.controllerPackage.RepairOrderController;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.modelPackage.RepairOrder;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

public class RepairOrderPanel extends JPanel {

    private RepairOrderController repairOrderController;

    private JTable repairTable;
    private RepairOrderTableModel tableModel;

    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;

    public RepairOrderPanel() throws ApplicationException {
        repairOrderController = new RepairOrderController();

        this.setLayout(new BorderLayout());

        createTable();
        createButtons();

        loadRepairOrders();
    }

    private void createTable() {
        tableModel = new RepairOrderTableModel();
        repairTable = new JTable(tableModel);
        repairTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(repairTable);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");
        refreshButton = new JButton("Rafraichir");

        addButton.addActionListener(event -> addRepairOrder());
        editButton.addActionListener(event -> editRepairOrder());
        deleteButton.addActionListener(event -> deleteRepairOrder());
        refreshButton.addActionListener(event -> loadRepairOrders());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadRepairOrders() {
        try {
            tableModel.setRepairOrders(repairOrderController.getAllRepairOrders());
        } catch (Exception exception) {
            showError(exception);
        }
    }

    private void addRepairOrder() {
        try {
            RepairOrderFormPanel formPanel = new RepairOrderFormPanel(null);

            int result = JOptionPane.showConfirmDialog(
                    this,
                    formPanel,
                    "Ajouter une reparation",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                RepairOrder repairOrder = formPanel.getRepairOrderFromForm();
                repairOrderController.addRepairOrder(repairOrder);
                loadRepairOrders();
            }

        } catch (Exception exception) {
            showError(exception);
        }
    }

    private void editRepairOrder() {
        int selectedRow = repairTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Veuillez selectionner une reparation.",
                    "Aucune selection",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            int modelRow = repairTable.convertRowIndexToModel(selectedRow);
            RepairOrder selectedRepairOrder = tableModel.getRepairOrderAt(modelRow);
            RepairOrderFormPanel formPanel = new RepairOrderFormPanel(selectedRepairOrder);

            int result = JOptionPane.showConfirmDialog(
                    this,
                    formPanel,
                    "Modifier une reparation",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                RepairOrder repairOrder = formPanel.getRepairOrderFromForm();
                repairOrderController.updateRepairOrder(repairOrder);
                loadRepairOrders();
            }

        } catch (Exception exception) {
            showError(exception);
        }
    }

    private void deleteRepairOrder() {
        int[] selectedRows = repairTable.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Veuillez selectionner au moins une reparation.",
                    "Aucune selection",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment supprimer les reparations selectionnees ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            ArrayList<RepairOrder> repairOrdersToDelete = new ArrayList<>();
            boolean containsLinkedInvoices = false;

            for (int selectedRow : selectedRows) {
                int modelRow = repairTable.convertRowIndexToModel(selectedRow);
                RepairOrder selectedRepairOrder = tableModel.getRepairOrderAt(modelRow);
                repairOrdersToDelete.add(selectedRepairOrder);

                if (repairOrderController.hasLinkedInvoice(selectedRepairOrder.getRepairId())) {
                    containsLinkedInvoices = true;
                }
            }

            boolean deleteLinkedInvoices = false;

            if (containsLinkedInvoices) {
                int linkedConfirmation = JOptionPane.showConfirmDialog(
                        this,
                        "Certaines reparations sont liees a une ou plusieurs factures. "
                                + "Voulez-vous supprimer aussi les factures liees ?",
                        "Reparations liees",
                        JOptionPane.YES_NO_OPTION
                );

                if (linkedConfirmation != JOptionPane.YES_OPTION) {
                    return;
                }

                deleteLinkedInvoices = true;
            }

            for (RepairOrder repairOrder : repairOrdersToDelete) {
                boolean linkedInvoice = repairOrderController.hasLinkedInvoice(repairOrder.getRepairId());
                repairOrderController.deleteRepairOrder(repairOrder.getRepairId(), linkedInvoice && deleteLinkedInvoices);
            }

            loadRepairOrders();

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
