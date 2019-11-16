package cl.talca.videogame.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Aircraft implements Shape{
    int x=400, y, width=30,height=30;
    boolean safe = true;
    int area = width * height;
    int destructionIterations = 1;
    int MAX_DESTRUCTION_ITERATIONS = 60;

    public Aircraft(int positionY){
        this.y = positionY;
    }

    public Point getP1(){
        return new Point(this.x, this.y);
    }

    public Point getP2(){
        return new Point(this.x + this.width, this.y + this.height);
    }

    public Rectangle getRectangle(){
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(this.safe == true){
            g2d.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
            g2d.fillRect(x, y, width, height);
        } else {
            this.drawAircraftDestruction(g2d);
        }
    }

    private void drawAircraftDestruction(Graphics2D g2d){
        Ellipse2D.Double circle1 = new Ellipse2D.Double(x, y, 30 + this.destructionIterations, 30 + this.destructionIterations);
        g2d.setColor(Color.ORANGE);
        g2d.fill(circle1);

        Ellipse2D.Double circle2 = new Ellipse2D.Double(x, y, 15 + this.destructionIterations, 15 + this.destructionIterations);
        g2d.setColor(Color.YELLOW);
        g2d.fill(circle2);
    }

    public void updatePosition() {
        if(this.safe == false){
            this.destructionIterations++;
        }
    }

    public Point getPosition(){
        return new Point(this.x , this.y);
    }

    public Boolean isVisible() {
        return this.safe || this.destructionIterations <= MAX_DESTRUCTION_ITERATIONS;
    }

    public void moveLeft() {
        this.x-=10;
    }

    public void moveRight() {
        this.x+=10;
    }
}
