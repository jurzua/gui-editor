package cl.talca.videogame.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet implements Shape{

    int x, y;
    int speedY;
    boolean alive = true;

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
        // if true, both shapes are destroyed

        if(this.x >= asteroid.x && this.x <= asteroid.x + asteroid.width &&
                this.y >= asteroid.y && this.y <= asteroid.y + asteroid.height){
            this.alive = false;
            return true;
        }
        return false;
    }

    public void updatePosition() {
        this.y -= speedY;
    }

    public Boolean isVisible() {
        return this.y > 0 && this.alive;
    }


}
