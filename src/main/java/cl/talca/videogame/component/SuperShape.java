package cl.talca.videogame.component;

import lombok.Data;

import java.awt.Point;

@Data
public abstract class SuperShape {

    protected int x, y, width, height, destructionIterations, MAX_DESTRUCTION_ITERATIONS;
    protected boolean inDestruction;

    public SuperShape(int x, int y, int width, int height, boolean inDestruction,
                      int destructionIterations, int MAX_DESTRUCTION_ITERATIONS) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.inDestruction = inDestruction;
        this.destructionIterations = destructionIterations;
        this.MAX_DESTRUCTION_ITERATIONS = MAX_DESTRUCTION_ITERATIONS;
    }

    public Point getP1(){
        return new Point(this.x, this.y);
    }

    public Point getP2(){
        return new Point(this.x + this.width, this.y + this.height);
    }

    public void destroyYourself() {
        this.inDestruction = true;
    }
}
