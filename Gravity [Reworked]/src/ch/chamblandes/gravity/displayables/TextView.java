package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Composite class of a Frame containing Text.
 *
 * @author jfperren
 */
public class TextView extends Frame {

    private Text text;

    public TextView(double x, double y, double width, double height, double border, Color borderColor, String text,
        String font, Color fontColor) {
        super(x, y, width, height, border, borderColor);
        this.text = new Text(x, y, text, font, fontColor);
    }

    public TextView(double x, double y, String message, String font, Color fontColor) {
        this(x, y, 0, 0, 0, Color.WHITE, message, font, fontColor);
    }

    public TextView(double x, double y, double width, double height, double border, Color borderColor) {
        this(x, y, width, height, border, borderColor, "", "Arial", Color.WHITE);
    }

    public void setText(String newText) {
        this.text.setText(newText);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.text.paint(g);
    }
}
