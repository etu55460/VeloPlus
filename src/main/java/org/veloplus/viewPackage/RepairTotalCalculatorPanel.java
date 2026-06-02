package org.veloplus.viewPackage;

import org.veloplus.controllerPackage.BusinessTaskController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.math.BigDecimal;

public class RepairTotalCalculatorPanel extends JPanel {

    private BusinessTaskController businessTaskController;

    private JTextField estimateAmountField;
    private JTextField laborHoursField;
    private JTextField hourlyRateField;

    public RepairTotalCalculatorPanel() {
        businessTaskController = new BusinessTaskController();

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 20));

        createFields();
    }

    private void createFields() {
        estimateAmountField = new JTextField(8);
        laborHoursField = new JTextField(8);
        hourlyRateField = new JTextField(8);

        JButton calculateButton = new JButton("Calculer total reparation");
        calculateButton.addActionListener(event -> calculateRepairTotal());

        this.add(new JLabel("Devis :"));
        this.add(estimateAmountField);
        this.add(new JLabel("Heures :"));
        this.add(laborHoursField);
        this.add(new JLabel("Tarif horaire :"));
        this.add(hourlyRateField);
        this.add(calculateButton);
    }

    private void calculateRepairTotal() {
        try {
            BigDecimal estimateAmount = parseNullableBigDecimal(
                    estimateAmountField.getText(),
                    "Le montant du devis"
            );
            BigDecimal laborHours = parseNullableBigDecimal(
                    laborHoursField.getText(),
                    "Le nombre d'heures"
            );
            BigDecimal hourlyRate = parseRequiredBigDecimal(
                    hourlyRateField.getText(),
                    "Le tarif horaire"
            );

            BigDecimal total = businessTaskController.calculateRepairTotal(
                    estimateAmount,
                    laborHours,
                    hourlyRate
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Montant total : " + total + " EUR",
                    "Resultat",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private BigDecimal parseRequiredBigDecimal(String text, String fieldName) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " est obligatoire.");
        }

        return parseBigDecimal(text, fieldName);
    }

    private BigDecimal parseNullableBigDecimal(String text, String fieldName) {
        if (text == null || text.isBlank()) {
            return null;
        }

        return parseBigDecimal(text, fieldName);
    }

    private BigDecimal parseBigDecimal(String text, String fieldName) {
        try {
            BigDecimal value = new BigDecimal(text);

            if (value.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(fieldName + " ne peut pas etre negatif.");
            }

            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(fieldName + " doit etre un nombre valide.", exception);
        }
    }
}
