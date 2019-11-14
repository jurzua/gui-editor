package cl.talca.videogame.component;

import cl.talca.videogame.MathHelper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Asteroid implements Shape {

    int x, y;
    int speedY;
    int screenHigh;
    boolean inDestruction = false;
    int destructionIterations = 1;
    int MAX_DESTRUCTION_ITERATIONS = 50;
    public int width = 40;
    public int height = 40;
    int destroyCount = 0;
    //int radius = this.height/2;
    //double area =  Math.PI * Math.pow(radius,2);

    public Asteroid(int screenWide, int screenHigh, int speedY) {
        this.x = MathHelper.randomNumber(0,screenWide);
        this.y = 0;
        this.screenHigh = screenHigh;
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

    public boolean aircraftDestruction(Aircraft aircraft) {
        //if(this.x >= asteroid.area && this.x <= asteroid.area && this.y >= asteroid.area && this.y <= asteroid.area){
        // if is true, both shapes are destroyed
        if( aircraft.x >= this.x && aircraft.x <= this.x + this.width &&
                aircraft.y >= this.y && aircraft.y <= this.y + this.height){
            aircraft.safe = false;
            return true;
        }
        return false;
    }

    public Boolean isVisible(){
        return this.y <= screenHigh && this.destructionIterations <= MAX_DESTRUCTION_ITERATIONS;
    }

}
