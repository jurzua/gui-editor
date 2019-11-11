package cl.talca.videogame.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Aircraft implements Shape{
    int x=400, y, width=30,height=30;
    boolean safe = true;

    public Aircraft(int positionY){
        this.y = positionY;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g2d.fillRect(x, y, width, height);
    }

    public void updatePosition() {
    }

    public boolean destroyAsteroid(Asteroid asteroid) {
        // if is true, both shapes are destroyed

        if(this.x >= asteroid.x && this.x <= asteroid.x + asteroid.width &&
                this.y >= asteroid.y && this.y <= asteroid.y + asteroid.height){
            this.safe = false;
            //this solution is only esthetic :P
            this.x = -1;
            this.y = -1;
            return true;
        }
        return false;
    }

    public Point getPosition(){
        return new Point(this.x , this.y);
    }

    public Boolean isVisible() {
        return this.safe;
    }

    public void moveLeft() {
        this.x-=10;
    }

    public void moveRight() {
        this.x+=10;
    }
}
