package ch.chamblandes.gravity.displayables;

import static ch.chamblandes.gravity.view.DisplayPanel.HEIGHT;

import java.awt.Color;

public abstract class Button extends FramedText {

    public Button(double x, double y, double width, double height, String value) {
        super(x, y, width, height, HEIGHT / 320, Color.white, value, "Impact 24", Color.white);
    }

    public void dispatchClick(double x, double y) {
        if (this.contains(x, y)) {
            this.onClick();
        }
    }

    public abstract void onClick();
}
