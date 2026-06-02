package org.veloplus.viewPackage;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BikeAnimationPanel extends JPanel implements Runnable {

    private int bikeX;
    private int bikeY;
    private int wheelAngle;
    private Thread animationThread;
    private boolean running;
    private long animationStartTime;

    private static final int ANIMATION_DURATION = 5000;

    public BikeAnimationPanel() {
        bikeX = 20;
        bikeY = 250;
        wheelAngle = 0;
        running = false;
        this.setBackground(Color.WHITE);
    }

    public synchronized void startAnimation() {
        if (animationThread == null || !animationThread.isAlive()) {
            running = true;
            animationStartTime = System.currentTimeMillis();
            animationThread = new Thread(this);
            animationThread.start();
        }
    }

    @Override
    public void run() {
        while (isRunning() && System.currentTimeMillis() - getAnimationStartTime() < ANIMATION_DURATION) {
            moveBike();
            repaint();

            try {
                Thread.sleep(30);
            } catch (InterruptedException exception) {
                stopAnimation();
                Thread.currentThread().interrupt();
            }
        }

        stopAnimation();
        repaint();
    }

    private synchronized boolean isRunning() {
        return running;
    }

    private synchronized int getBikeX() {
        return bikeX;
    }

    private synchronized int getWheelAngle() {
        return wheelAngle;
    }

    private synchronized long getAnimationStartTime() {
        return animationStartTime;
    }

    private synchronized void moveBike() {
        bikeX += 4;
        wheelAngle = (wheelAngle + 12) % 360;

        if (bikeX > getWidth()) {
            bikeX = -120;
        }
    }

    public synchronized void stopAnimation() {
        running = false;
    }

    @Override
    public void removeNotify() {
        stopAnimation();
        super.removeNotify();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawString("VeloPlus - Gestion des reparations et ventes de velos", 260, 100);

        drawBike(graphics, getBikeX(), bikeY, getWheelAngle());
    }

    private void drawBike(Graphics graphics, int x, int y, int angle) {
        graphics.setColor(Color.BLACK);

        graphics.drawOval(x, y, 50, 50);
        graphics.drawOval(x + 90, y, 50, 50);
        drawWheelSpokes(graphics, x + 25, y + 25, angle);
        drawWheelSpokes(graphics, x + 115, y + 25, angle);

        graphics.drawLine(x + 25, y + 25, x + 60, y - 20);
        graphics.drawLine(x + 60, y - 20, x + 115, y + 25);
        graphics.drawLine(x + 25, y + 25, x + 115, y + 25);
        graphics.drawLine(x + 60, y - 20, x + 75, y + 25);

        graphics.drawLine(x + 60, y - 20, x + 55, y - 40);
        graphics.drawLine(x + 45, y - 40, x + 70, y - 40);

        graphics.drawLine(x + 115, y + 25, x + 130, y - 25);
        graphics.drawLine(x + 130, y - 25, x + 145, y - 25);

        graphics.setColor(Color.BLUE);
        graphics.drawString("VeloPlus", x + 35, y + 80);
    }

    private void drawWheelSpokes(Graphics graphics, int centerX, int centerY, int angle) {
        int radius = 25;

        for (int i = 0; i < 4; i++) {
            double radians = Math.toRadians(angle + i * 90);
            int x = centerX + (int) (Math.cos(radians) * radius);
            int y = centerY + (int) (Math.sin(radians) * radius);
            graphics.drawLine(centerX, centerY, x, y);
        }
    }
}
