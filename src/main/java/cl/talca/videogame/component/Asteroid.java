package cl.talca.videogame.component;

import cl.talca.videogame.MathHelper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Asteroid implements Shape {

    double x, y;
    double speedY;
    int screenHigh;

    public Asteroid(int screenWide, int screenHigh, double speedY) {
        this.x = MathHelper.randomNumber(0,screenWide);
        this.y = 0;
        this.screenHigh = screenHigh;
        this.speedY = speedY;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 40, 40);
        g2d.setColor(Color.BLUE);
        g2d.fill(circle);
    }

    public void updatePosition() {
        this.y += speedY;
    }

    public Boolean isVisible(){
        return this.y <= screenHigh;
    }

}
