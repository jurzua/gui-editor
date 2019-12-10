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

    public Boolean isVisible(){
        return this.y <= this.screenHigh && this.destructionIterations <= this.maxDestructionIterations;
    }
}
