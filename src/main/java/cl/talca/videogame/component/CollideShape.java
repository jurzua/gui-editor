package cl.talca.videogame.component;

import java.awt.*;

public class CollideShape extends DestructibleShape {

    int destroyCount;
    int screenHigh;

    public CollideShape(int x, int y, int width, int height, int  maxDestructionIterations, int destroyCount, int screenHigh) {
        super(x, y, width, height, maxDestructionIterations);
        this.destroyCount = destroyCount;
        this.screenHigh = screenHigh;
    }

    public boolean collidesWith(Aircraft aircraft) {
        return doOverlap(this.getP1(), aircraft.getP1(), this.getP2(), aircraft.getP2());
    }

    public  boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        if (l1.x >= r2.x || l2.x <= r1.x && l1.y <= r2.y || l2.y <= r1.y) {
            return false;
        }
        return true;
    }

    public Boolean isVisible(){
        return this.y <= this.screenHigh && this.destructionIterations <= this.maxDestructionIterations;
    }
}
