package cl.talca.videogame.component;

public class DestructibleShape extends SuperShape {

    int destructionIterations, maxDestructionIterations;
    protected boolean inDestruction;

    public DestructibleShape(int x, int y, int width, int height, int  maxDestructionIterations) {
        super(x, y, width, height);
        this.inDestruction = false;
        this.destructionIterations = 1;
        this.maxDestructionIterations = maxDestructionIterations;
    }

    public void destroyYourself() {
        this.inDestruction = true;
    }
}
