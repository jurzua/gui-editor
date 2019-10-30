package cl.talca.videogame.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet implements Shape{

    int x, y;
    int speedY;

    public Bullet (int x,int y){
        this.x = x;
        this.y = y;
        this.speedY = 3;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);
        g2d.setColor(Color.RED);
        g2d.fill(circle);
    }

    public boolean destroyAsteroid(Asteroid asteroid) {
        //todo
        return false;
    }

    public void updatePosition() {
        this.y -= speedY;
    }

    public Boolean isVisible() {
        return this.y > 0;
    }


}
