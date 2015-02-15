package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Composite class of a Frame containing Text.
 *
 * @author jfperren
 */
public class FramedText extends Frame {

    private Text text;

    public FramedText(double x, double y, double width, double height, double border, Color borderColor, String text,
        String font, Color fontColor) {
        super(x, y, width, height, border, borderColor);
        this.text = new Text(x, y, text, font, fontColor);
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
