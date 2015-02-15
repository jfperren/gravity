package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Writing extends Displayable {

    String text;
    String font;
    Color fontColor;

    public Writing(String message, String font, int x, int y, Color fontColor) {
        super(x, y);
        this.text = message;
        this.font = font;
        this.fontColor = fontColor;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.fontColor);
        g.setFont(Font.decode(this.font));
        g.drawString(this.text, (int) this.getX(), (int) this.getY());
    }
}