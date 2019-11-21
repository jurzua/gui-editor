package cl.talca.videogame.component;

import cl.talca.videogame.MathHelper;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class Asteroid implements ShapeInterface {

    int x, y;
    int speedY;
    int screenHigh;
    boolean inDestruction = false;
    int destructionIterations = 1;
    int MAX_DESTRUCTION_ITERATIONS = 50;
    public int width = 40;
    public int height = 40;
    int destroyCount = 0;

    public Asteroid(int screenWide, int screenHigh, int speedY) {
        this.x = MathHelper.randomNumber(0,screenWide);
        this.y = 0;
        this.screenHigh = screenHigh;
        this.speedY = speedY;
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
        if(this.inDestruction){
            this.drawInDestruction(g2d);
        } else {
            this.drawNormal(g2d);
        }
    }

    private void drawInDestruction(Graphics2D g2d){
        Ellipse2D.Double circle1 = new Ellipse2D.Double(x, y, 10 + this.destructionIterations, 10 + this.destructionIterations);
        g2d.setColor(Color.ORANGE);
        g2d.fill(circle1);

        Ellipse2D.Double circle2 = new Ellipse2D.Double(x, y, 5 + this.destructionIterations, 5 + this.destructionIterations);
        g2d.setColor(Color.YELLOW);
        g2d.fill(circle2);
    }

    private void drawNormal(Graphics2D g2d){
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, this.width, this.height);
        g2d.setColor(Color.BLUE);
        g2d.fill(circle);
    }

    public void destroyed() {
        this.inDestruction = true;
    }

    public void updatePosition() {
        if(!this.inDestruction){
            this.y += speedY;
            this.destroyCount += 1;
        } else {
            this.destructionIterations++;
        }
    }

    public boolean destroyAircraft(Aircraft aircraft) {
        //if(this.x >= asteroid.area && this.x <= asteroid.area && this.y >= asteroid.area && this.y <= asteroid.area){
        // if is true, both shapes are destroyed
        if( doOverlap(this.getP1(), aircraft.getP1(), this.getP2(), aircraft.getP2())){
            aircraft.safe = false;
            return true;
        }
        return false;
    }

    static  boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        if (l1.x >= r2.x || l2.x <= r1.x && l1.y <= r2.y || l2.y <= r1.y) {
            return false;
        }
        return true;
    }

    public Boolean isVisible(){
        return this.y <= screenHigh && this.destructionIterations <= MAX_DESTRUCTION_ITERATIONS;
    }

}
