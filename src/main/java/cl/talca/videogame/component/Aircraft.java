package cl.talca.videogame.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Aircraft implements Shape{
    int x=400, y, width=30,height=30;


    public Aircraft(int positionY){
        this.y = positionY;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    public void updatePosition() {

    }

    public Boolean isVisible() {
        return true;
    }

    public void moveLeft() {
        this.x--;
    }

    public void moveRight() {
        this.x++;
    }
}
