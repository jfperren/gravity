/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.chamblandes.gravity;

/**
 * @author julienperrenoud
 */
public class Main {

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        projet applet = new projet();
        applet.init();
        applet.start();

        javax.swing.JFrame window = new javax.swing.JFrame("Gravity");
        window.setContentPane(applet);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setBounds(0, 0, 410, 810);
    }

}
