package ch.chamblandes.gravity.model;

import ch.chamblandes.gravity.view.DisplayPanel;

public class GravityLauncher {
    public static void main(String[] args) {
        GravityApplet applet = new GravityApplet();
        applet.init();
        applet.start();

        javax.swing.JFrame window = new javax.swing.JFrame("Gravity");
        window.setContentPane(applet);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setBounds(DisplayPanel.MARGIN_LEFT, DisplayPanel.MARGIN_TOP, DisplayPanel.WIDTH
            + (4 * DisplayPanel.MARGIN_LEFT), DisplayPanel.HEIGHT + (8 * DisplayPanel.MARGIN_TOP));
        window.setVisible(true);
    }
}
