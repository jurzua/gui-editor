package cl.talca.videogame.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet implements ShapeInterface {

    boolean alive = true;
    private int speedY;
    int x, y, width, height;


    public Bullet (int x,int y){
        //super(x, y, 10, 10);
        this.x = x;
        this.y = y;
        this.height = 10;
        this.width = 10;
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
