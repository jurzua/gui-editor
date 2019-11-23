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

    public Point getP1(){
        return new Point(this.x, this.y);
    }

    public Point getP2(){
        return new Point(this.x + this.width, this.y + this.height);
    }

}
