package cl.talca.videogame.component;

import cl.talca.videogame.MathHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class LifeCoin extends CollideShape implements ShapeInterface {

    private int speedY;
    private BufferedImage image = null;
    private JPanel observer = null;

    public LifeCoin(int screenWide, int screenHigh, int speedY, BufferedImage image, JPanel observer) {
        super(MathHelper.randomNumber(0,screenWide),0, 30, 30, 1,0, screenHigh);
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
        //Ellipse2D.Double circle1 = new Ellipse2D.Double(x, y, 10 + this.destructionIterations, 1 + this.destructionIterations);
        g2d.drawImage(image, x, y, observer);
        //g2d.fill(circle1);
    }

    private void drawNormal(Graphics2D g2d){
        g2d.drawImage(image, x, y, observer);
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
