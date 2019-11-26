package cl.talca.videogame.component;

import cl.talca.videogame.MathHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Asteroid extends DestructibleShape implements ShapeInterface {

    private static int MAX_DESTRUCTION_ITERATIONS = 50;
    private int speedY;
    int screenHigh;
    int destroyCount = 0;
    private BufferedImage image = null;
    private JPanel observer = null;
    int LifeCounter = 3;

    public Asteroid(int screenWide, int screenHigh, int speedY, BufferedImage image, JPanel observer) {
        super(MathHelper.randomNumber(0,screenWide), 0, 40, 40, MAX_DESTRUCTION_ITERATIONS);
        this.screenHigh = screenHigh;
        this.image = image;
        this.observer = observer;
        this.speedY = speedY;
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
        g2d.drawImage(image, x, y, observer);
        //Ellipse2D.Double circle = new Ellipse2D.Double(x, y, this.width, this.height);
        //        g2d.setColor(Color.BLUE);
        //        g2d.fill(circle);
    }

    public void updatePosition() {
        if(!this.inDestruction){
            this.y += speedY;
            this.destroyCount += 1;
        } else {
            this.destructionIterations++;
        }
    }

    public boolean collidesWith(Aircraft aircraft) {
        return doOverlap(this.getP1(), aircraft.getP1(), this.getP2(), aircraft.getP2());
    }
    //static
    public  boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        if (l1.x >= r2.x || l2.x <= r1.x && l1.y <= r2.y || l2.y <= r1.y) {
            return false;
        }
        return true;
    }

    public Boolean isVisible(){
        return this.y <= screenHigh && this.destructionIterations <= MAX_DESTRUCTION_ITERATIONS;
    }

}
