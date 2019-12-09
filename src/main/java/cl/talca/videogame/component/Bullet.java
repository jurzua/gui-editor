package cl.talca.videogame.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet extends SuperShape implements ShapeInterface {

    boolean alive = true;
    private int speedY;

    public Bullet (int x,int y){
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
            if(this.x >= asteroid.x && this.x <= asteroid.x + asteroid.width &&
                    this.y >= asteroid.y && this.y <= asteroid.y + asteroid.height){
                this.alive = false;
                return true;
            }
            return false;
        }

    //public boolean collidesWith(Asteroid asteroid) {
    //        return doOverlap(this.getP1(), asteroid.getP1(), this.getP2(), asteroid.getP2());
    //    }
    //
    //    public  boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
    //        if (l1.x >= r2.x || l2.x <= r1.x && l1.y >= r2.y || l2.y <= r1.y) {
    //            return false;
    //        }
    //        return true;
    //    }

    public void updatePosition() {
        this.y -= speedY;
    }

    public Boolean isVisible() {
        return this.y > 0 && this.alive;
    }


}
