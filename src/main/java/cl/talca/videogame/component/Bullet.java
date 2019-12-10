package cl.talca.videogame.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet extends SuperShape implements ShapeInterface {

    boolean alive = true;
    private int speedY;

    public Bullet(int x, int y) {
        super(x, y, 10, 10);
        this.speedY = 3;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, this.width, this.height);
        g2d.setColor(Color.RED);
        g2d.fill(circle);
    }

    public boolean collidesWith(Asteroid asteroid) {
        // if true, both shapes are destroyed
        if (this.x >= asteroid.x && this.x <= asteroid.x + asteroid.width &&
                this.y >= asteroid.y && this.y <= asteroid.y + asteroid.height) {
            this.alive = false;
            return true;
        }
        return false;
    }

    public boolean doOverlap(Asteroid asteroid) {
        return (this.x >= asteroid.x && this.x <= asteroid.x + asteroid.width &&
                this.y >= asteroid.y && this.y <= asteroid.y + asteroid.height);
    }

    public void updatePosition() {
        this.y -= speedY;
    }

    public Boolean isVisible() {
        return this.y > 0 && this.alive;
    }


}
