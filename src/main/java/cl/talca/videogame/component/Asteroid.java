package cl.talca.videogame.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Asteroid implements Shape {

    double x, y;
    double speedY;

    public Asteroid(double x, double speedY) {
        this.x = x;
        this.y = 0;
        this.speedY = speedY;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 40, 40);

        g2d.setColor(Color.BLUE);
        g2d.fill(circle);
    }

    @Override
    public void updatePosition() {
        this.y += speedY;
    }
}
