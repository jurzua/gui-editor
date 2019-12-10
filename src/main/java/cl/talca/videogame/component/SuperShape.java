package cl.talca.videogame.component;

import lombok.Data;

import java.awt.Point;

@Data
public abstract class SuperShape {

    protected int x, y, width, height;

    public SuperShape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(SuperShape shape) {
        return doOverlap(this.getP1(), shape.getP1(), this.getP2(), shape.getP2());
    }

    public  boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        if (l1.x >= r2.x || l2.x <= r1.x && l1.y <= r2.y || l2.y <= r1.y) {
            return false;
        }
        return true;
    }


    public Point getP1(){
        return new Point(this.x, this.y);
    }

    public Point getP2(){
        return new Point(this.x + this.width, this.y + this.height);
    }


}
