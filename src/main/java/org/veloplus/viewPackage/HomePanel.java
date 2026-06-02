package org.veloplus.viewPackage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

public class HomePanel extends JPanel {

    private JLabel welcomeLabel;
    private BikeAnimationPanel bikeAnimationPanel;

    public HomePanel() {
        this.setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Bienvenue dans l'application VeloPlus");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26));

        bikeAnimationPanel = new BikeAnimationPanel();

        this.add(welcomeLabel, BorderLayout.NORTH);
        this.add(bikeAnimationPanel, BorderLayout.CENTER);

        bikeAnimationPanel.startAnimation();
    }
}
