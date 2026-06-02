package org.veloplus.viewPackage;

import org.veloplus.controllerPackage.ApplicationController;
import org.veloplus.exceptionPackage.ApplicationException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private Container frameContainer;
    private JPanel currentPanel;
    private ApplicationController controller;

    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenu homeMenu;
    private JMenu repairMenu;
    private JMenu searchMenu;

    private JMenuItem quitItem;
    private JMenuItem homeItem;
    private JMenuItem repairOrderItem;
    private JMenuItem repairTotalCalculatorItem;
    private JMenuItem repairsByClientItem;
    private JMenuItem bikeOrdersBetweenDatesItem;
    private JMenuItem invoicesByPaidStatusItem;

    public MainWindow() {
        super("VeloPlus");
        controller = new ApplicationController();

        setBounds(100, 100, 1100, 750);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                quitApplication();
            }
        });

        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        createMenuBar();
        showHomePanel();

        setVisible(true);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("Fichier");
        homeMenu = new JMenu("Accueil");
        repairMenu = new JMenu("Reparations");
        searchMenu = new JMenu("Recherches");

        quitItem = new JMenuItem("Quitter");
        homeItem = new JMenuItem("Afficher l'accueil");
        repairOrderItem = new JMenuItem("Gerer les reparations");
        repairTotalCalculatorItem = new JMenuItem("Calculer total reparation");
        repairsByClientItem = new JMenuItem("Reparations par client");
        bikeOrdersBetweenDatesItem = new JMenuItem("Commandes velos entre dates");
        invoicesByPaidStatusItem = new JMenuItem("Factures par statut de paiement");

        quitItem.addActionListener(event -> quitApplication());

        homeItem.addActionListener(event -> showHomePanel());

        repairOrderItem.addActionListener(event -> {
            try {
                changePanel(new RepairOrderPanel());
            } catch (ApplicationException exception) {
                showError(exception);
            }
        });

        repairTotalCalculatorItem.addActionListener(event ->
                changePanel(new RepairTotalCalculatorPanel())
        );

        repairsByClientItem.addActionListener(event ->
                showSearchPanel(SearchPanel.SearchMode.REPAIRS_BY_CLIENT)
        );

        bikeOrdersBetweenDatesItem.addActionListener(event ->
                showSearchPanel(SearchPanel.SearchMode.BIKE_ORDERS_BETWEEN_DATES)
        );

        invoicesByPaidStatusItem.addActionListener(event ->
                showSearchPanel(SearchPanel.SearchMode.INVOICES_BY_PAID_STATUS)
        );

        fileMenu.add(quitItem);
        homeMenu.add(homeItem);
        repairMenu.add(repairOrderItem);
        repairMenu.add(repairTotalCalculatorItem);
        searchMenu.add(repairsByClientItem);
        searchMenu.add(bikeOrdersBetweenDatesItem);
        searchMenu.add(invoicesByPaidStatusItem);

        menuBar.add(fileMenu);
        menuBar.add(homeMenu);
        menuBar.add(repairMenu);
        menuBar.add(searchMenu);

        this.setJMenuBar(menuBar);
    }

    private void showHomePanel() {
        changePanel(new HomePanel());
    }

    private void showSearchPanel(SearchPanel.SearchMode searchMode) {
        try {
            changePanel(new SearchPanel(searchMode));
        } catch (ApplicationException exception) {
            showError(exception);
        }
    }

    private void quitApplication() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment quitter l'application ?",
                "Quitter",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            try {
                controller.closeApplication();
            } catch (ApplicationException exception) {
                JOptionPane.showMessageDialog(
                        this,
                        exception.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            dispose();
            System.exit(0);
        }
    }

    private void changePanel(JPanel newPanel) {
        if (currentPanel != null) {
            frameContainer.remove(currentPanel);
        }

        currentPanel = newPanel;
        frameContainer.add(currentPanel, BorderLayout.CENTER);

        frameContainer.revalidate();
        frameContainer.repaint();
    }

    private void showError(ApplicationException exception) {
        JOptionPane.showMessageDialog(
                this,
                exception.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
