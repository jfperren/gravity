package ch.chamblandes.gravity.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import ch.chamblandes.gravity.displayables.Button;
import ch.chamblandes.gravity.displayables.Displayable;
import ch.chamblandes.gravity.displayables.TextView;
import ch.chamblandes.gravity.model.GravityApplet;
import ch.chamblandes.gravity.model.ScreenManager.Screen;

public class DisplayPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final int HEIGHT = 750; // hauteur du dessin
    public static final int WIDTH = 375; // largeur du
    public static final int MARGIN_TOP = 10; // marge en haut
    public static final int MARGIN_LEFT = 10; // marge à gauche

    private GravityApplet applet;

    public DisplayPanel(GravityApplet applet) {
        super();

        this.applet = applet;
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background
        this.applet.getRessourceManager().getBackground().paint(g);

        // Fetch screen to display
        Screen screen = this.applet.getScreenManager().getScreen();

        // Draw images
        for (Displayable displayable : this.applet.getRessourceManager().getDrawingsForScreen(screen)) {
            displayable.paint(g);
        }

        // Draw buttons
        for (Button button : this.applet.getRessourceManager().getButtonsForScreen(screen)) {
            button.paint(g);
        }

        for (TextView textView : this.applet.getRessourceManager().getTextViewsForScreen(screen)) {
            textView.paint(g);
        }

    }
}