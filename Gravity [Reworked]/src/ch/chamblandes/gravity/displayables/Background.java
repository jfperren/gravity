package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;

public class Background extends RectangularDisplayable {

    public Background(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void defiler(double vy) {
        // if(y<dessH+height){
        if (this.getY() > this.getHeight()) {
            this.setY(0);
        } else if (this.getY() < 0) {
            this.setY(this.getHeight());
        } else {
            // Nothing
        }

        this.setY(this.getY() + (vy * 0.6));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0, 0, 15));
        g.fillRect((int) this.getX(), (int) (this.getY() - this.getHeight()), (int) this.getWidth(),
            (int) this.getHeight() * 2);

        // Premiere série
        g.setColor(Color.white);
        g.fillOval(60, (int) this.getY() + 40, 3, 3);
        g.fillOval(330, (int) this.getY() + 76, 3, 3);
        g.fillOval(200, (int) this.getY() + 134, 3, 3);
        g.fillOval(120, (int) this.getY() + 200, 3, 3);
        g.fillOval(280, (int) this.getY() + 230, 3, 3);
        g.fillOval(180, (int) this.getY() + 295, 3, 3);
        g.fillOval(100, (int) this.getY() + 326, 3, 3);
        g.fillOval(310, (int) this.getY() + 284, 3, 3);
        g.fillOval(65, (int) this.getY() + 450, 3, 3);
        g.fillOval(220, (int) this.getY() + 480, 3, 3);
        g.fillOval(30, (int) this.getY() + 540, 3, 3);
        g.fillOval(260, (int) this.getY() + 576, 3, 3);
        g.fillOval(200, (int) this.getY() + 634, 3, 3);
        g.fillOval(90, (int) this.getY() + 700, 3, 3);
        g.fillOval(145, (int) this.getY() + 730, 3, 3);
        // Deuxieme série
        g.fillOval(60, ((int) this.getY() + 40) - 750, 3, 3);
        g.fillOval(330, ((int) this.getY() + 76) - 750, 3, 3);
        g.fillOval(200, ((int) this.getY() + 134) - 750, 3, 3);
        g.fillOval(120, ((int) this.getY() + 200) - 750, 3, 3);
        g.fillOval(280, ((int) this.getY() + 230) - 750, 3, 3);
        g.fillOval(180, ((int) this.getY() + 295) - 750, 3, 3);
        g.fillOval(100, ((int) this.getY() + 326) - 750, 3, 3);
        g.fillOval(310, ((int) this.getY() + 284) - 750, 3, 3);
        g.fillOval(65, ((int) this.getY() + 450) - 750, 3, 3);
        g.fillOval(220, ((int) this.getY() + 480) - 750, 3, 3);
        g.fillOval(30, ((int) this.getY() + 540) - 750, 3, 3);
        g.fillOval(260, ((int) this.getY() + 576) - 750, 3, 3);
        g.fillOval(200, ((int) this.getY() + 634) - 750, 3, 3);
        g.fillOval(90, ((int) this.getY() + 700) - 750, 3, 3);
        g.fillOval(145, ((int) this.getY() + 730) - 750, 3, 3);

    }
}
