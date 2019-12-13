package cl.talca.videogame.component;


import cl.talca.videogame.utils.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Asteroid extends CollideShape implements ShapeInterface {

    //private static int MAX_DESTRUCTION_ITERATIONS = 50;
    private int speedY;
    private BufferedImage image = null;
    private JPanel observer = null;

    public Asteroid(int screenWide, int screenHigh, int speedY, BufferedImage image, JPanel observer) {
        super(MathUtils.randomNumber(0,screenWide), 0, 40, 40, 50, 0, screenHigh);
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
            g2d.setColor(Color.YELLOW);
            g2d.drawRect(this.x, this.y, this.width, this.height);
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
}
