package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text extends Displayable {

    String text;
    String font;
    Color fontColor;

    public Text(double x, double y, String message, String font, Color fontColor) {
        super(x, y);
        this.text = message;
        this.font = font;
        this.fontColor = fontColor;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.fontColor);
        g.setFont(Font.decode(this.font));
        g.drawString(this.text, (int) this.getX(), (int) this.getY());
    }
}