package org.veloplus.viewPackage;

import org.veloplus.controllerPackage.ClientController;
import org.veloplus.controllerPackage.StatusController;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.modelPackage.Client;
import org.veloplus.modelPackage.RepairOrder;
import org.veloplus.modelPackage.Status;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class RepairOrderFormPanel extends JPanel {

    private ClientController clientController;
    private StatusController statusController;

    private RepairOrder repairOrderToEdit;

    private JComboBox<Client> clientComboBox;
    private JTextField problemDescriptionField;
    private JTextField depositDateField;
    private JTextField appointmentDateField;
    private JTextField estimateAmountField;
    private JCheckBox acceptedEstimateCheckBox;
    private JTextField laborHoursField;
    private JComboBox<Status> statusComboBox;
    private JTextArea notesArea;

    public RepairOrderFormPanel(RepairOrder repairOrderToEdit) throws ApplicationException {
        this.repairOrderToEdit = repairOrderToEdit;

        clientController = new ClientController();
        statusController = new StatusController();

        this.setLayout(new GridLayout(9, 2, 10, 10));

        createFields();
        loadComboBoxes();

        if (repairOrderToEdit != null) {
            fillFields(repairOrderToEdit);
        }
    }

    private void createFields() {
        clientComboBox = new JComboBox<>();
        problemDescriptionField = new JTextField();
        depositDateField = new JTextField();
        appointmentDateField = new JTextField();
        estimateAmountField = new JTextField();
        acceptedEstimateCheckBox = new JCheckBox("Devis accepte");
        laborHoursField = new JTextField();
        statusComboBox = new JComboBox<>();
        notesArea = new JTextArea(3, 20);

        this.add(new JLabel("Client :"));
        this.add(clientComboBox);

        this.add(new JLabel("Description probleme :"));
        this.add(problemDescriptionField);

        this.add(new JLabel("Date depot YYYY-MM-DD :"));
        this.add(depositDateField);

        this.add(new JLabel("Date rendez-vous YYYY-MM-DD (facultatif) :"));
        this.add(appointmentDateField);

        this.add(new JLabel("Montant devis :"));
        this.add(estimateAmountField);

        this.add(new JLabel("Devis accepte :"));
        this.add(acceptedEstimateCheckBox);

        this.add(new JLabel("Heures main-d'oeuvre (facultatif) :"));
        this.add(laborHoursField);

        this.add(new JLabel("Statut :"));
        this.add(statusComboBox);

        this.add(new JLabel("Notes (facultatif) :"));
        this.add(notesArea);
    }

    private void loadComboBoxes() throws ApplicationException {
        ArrayList<Client> clients = clientController.getAllClients();
        for (Client client : clients) {
            clientComboBox.addItem(client);
        }

        ArrayList<Status> statuses = statusController.getAllStatuses();
        for (Status status : statuses) {
            statusComboBox.addItem(status);
        }
    }

    private void fillFields(RepairOrder repairOrder) {
        selectClient(repairOrder.getClient());
        selectStatus(repairOrder.getStatus());

        problemDescriptionField.setText(repairOrder.getProblemDescription());

        if (repairOrder.getDepositDate() != null) {
            depositDateField.setText(repairOrder.getDepositDate().toString());
        }

        if (repairOrder.getAppointmentDate() != null) {
            appointmentDateField.setText(repairOrder.getAppointmentDate().toString());
        }

        if (repairOrder.getEstimateAmount() != null) {
            estimateAmountField.setText(repairOrder.getEstimateAmount().toString());
        }

        acceptedEstimateCheckBox.setSelected(Boolean.TRUE.equals(repairOrder.getAcceptedEstimate()));

        if (repairOrder.getLaborHours() != null) {
            laborHoursField.setText(repairOrder.getLaborHours().toString());
        }

        if (repairOrder.getNotes() != null) {
            notesArea.setText(repairOrder.getNotes());
        }
    }

    private void selectClient(Client clientToSelect) {
        if (clientToSelect == null) {
            return;
        }

        for (int i = 0; i < clientComboBox.getItemCount(); i++) {
            Client client = clientComboBox.getItemAt(i);

            if (client.getClientId().equals(clientToSelect.getClientId())) {
                clientComboBox.setSelectedIndex(i);
                return;
            }
        }
    }

    private void selectStatus(Status statusToSelect) {
        if (statusToSelect == null) {
            return;
        }

        for (int i = 0; i < statusComboBox.getItemCount(); i++) {
            Status status = statusComboBox.getItemAt(i);

            if (status.getStatusId().equals(statusToSelect.getStatusId())) {
                statusComboBox.setSelectedIndex(i);
                return;
            }
        }
    }

    public RepairOrder getRepairOrderFromForm() throws ApplicationException {
        Client selectedClient = (Client) clientComboBox.getSelectedItem();
        Status selectedStatus = (Status) statusComboBox.getSelectedItem();
        String problemDescription = problemDescriptionField.getText();

        ValidationResult validationResult = validateForm(selectedClient, problemDescription, selectedStatus);

        if (!validationResult.errorMessages.isEmpty()) {
            throw new ApplicationException(String.join("\n", validationResult.errorMessages));
        }

        RepairOrder repairOrder;

        if (repairOrderToEdit == null) {
            repairOrder = new RepairOrder();
        } else {
            repairOrder = repairOrderToEdit;
        }

        repairOrder.setClient(selectedClient);
        repairOrder.setProblemDescription(problemDescription);
        repairOrder.setDepositDate(validationResult.depositDate);
        repairOrder.setAppointmentDate(validationResult.appointmentDate);
        repairOrder.setEstimateAmount(validationResult.estimateAmount);
        repairOrder.setAcceptedEstimate(acceptedEstimateCheckBox.isSelected());
        repairOrder.setLaborHours(validationResult.laborHours);
        repairOrder.setStatus(selectedStatus);
        repairOrder.setNotes(notesArea.getText());

        return repairOrder;
    }

    private ValidationResult validateForm(Client selectedClient, String problemDescription, Status selectedStatus) {
        ValidationResult validationResult = new ValidationResult();
        ArrayList<String> missingFields = new ArrayList<>();

        if (selectedClient == null) {
            missingFields.add("- Client");
        }

        if (problemDescription == null || problemDescription.isBlank()) {
            missingFields.add("- Description probleme");
        }

        if (depositDateField.getText() == null || depositDateField.getText().isBlank()) {
            missingFields.add("- Date depot");
        }

        if (estimateAmountField.getText() == null || estimateAmountField.getText().isBlank()) {
            missingFields.add("- Montant devis");
        }

        if (selectedStatus == null) {
            missingFields.add("- Statut");
        }

        if (!missingFields.isEmpty()) {
            validationResult.errorMessages.add(
                    "Les champs obligatoires suivants doivent etre remplis :\n"
                            + String.join("\n", missingFields)
            );
        }

        validationResult.depositDate = tryParseRequiredLocalDate(
                depositDateField.getText(),
                "La date de depot",
                validationResult.errorMessages
        );
        validationResult.appointmentDate = tryParseNullableLocalDate(
                appointmentDateField.getText(),
                "La date de rendez-vous",
                validationResult.errorMessages
        );
        validationResult.estimateAmount = tryParseRequiredBigDecimal(
                estimateAmountField.getText(),
                "Le montant du devis",
                validationResult.errorMessages
        );
        validationResult.laborHours = tryParseNullableBigDecimal(
                laborHoursField.getText(),
                "Le nombre d'heures de main-d'oeuvre",
                validationResult.errorMessages
        );

        if (validationResult.depositDate != null
                && validationResult.appointmentDate != null
                && validationResult.appointmentDate.isBefore(validationResult.depositDate)) {
            validationResult.errorMessages.add(
                    "La date de rendez-vous ne peut pas etre avant la date de depot."
            );
        }

        return validationResult;
    }

    private LocalDate tryParseRequiredLocalDate(String text, String fieldName, ArrayList<String> errorMessages) {
        if (text == null || text.isBlank()) {
            return null;
        }

        return tryParseLocalDate(text, fieldName, errorMessages);
    }

    private LocalDate tryParseNullableLocalDate(String text, String fieldName, ArrayList<String> errorMessages) {
        if (text == null || text.isBlank()) {
            return null;
        }

        return tryParseLocalDate(text, fieldName, errorMessages);
    }

    private LocalDate tryParseLocalDate(String text, String fieldName, ArrayList<String> errorMessages) {
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException exception) {
            errorMessages.add(fieldName + " doit respecter le format YYYY-MM-DD.");
            return null;
        }
    }

    private BigDecimal tryParseRequiredBigDecimal(String text, String fieldName, ArrayList<String> errorMessages) {
        if (text == null || text.isBlank()) {
            return null;
        }

        return tryParseBigDecimal(text, fieldName, errorMessages);
    }

    private BigDecimal tryParseNullableBigDecimal(String text, String fieldName, ArrayList<String> errorMessages) {
        if (text == null || text.isBlank()) {
            return null;
        }

        return tryParseBigDecimal(text, fieldName, errorMessages);
    }

    private BigDecimal tryParseBigDecimal(String text, String fieldName, ArrayList<String> errorMessages) {
        try {
            BigDecimal value = new BigDecimal(text);

            if (value.compareTo(BigDecimal.ZERO) < 0) {
                errorMessages.add(fieldName + " ne peut pas etre negatif.");
                return null;
            }

            return value;
        } catch (NumberFormatException exception) {
            errorMessages.add(fieldName + " doit etre un nombre valide.");
            return null;
        }
    }

    private static class ValidationResult {
        private ArrayList<String> errorMessages = new ArrayList<>();
        private LocalDate depositDate;
        private LocalDate appointmentDate;
        private BigDecimal estimateAmount;
        private BigDecimal laborHours;
    }
}
