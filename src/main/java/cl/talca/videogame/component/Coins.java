package cl.talca.videogame.component;

import cl.talca.videogame.MathHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Coins extends CollideShape implements ShapeInterface {

    public CoinType type;
    private int speedY;
    private BufferedImage image = null;
    private JPanel observer = null;

    public Coins(CoinType type, int screenWide, int screenHigh, int speedY, BufferedImage image, JPanel observer) {
        super(MathHelper.randomNumber(0,screenWide),0, 30, 30, 60,0, screenHigh);
        this.type = type;
        this.screenHigh = screenHigh;
        this.image = image;
        this.observer = observer;
        this.speedY = speedY;
    }

    public CoinType getType() {
        return this.type;
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
        g2d.setColor(Color.BLACK);
        g2d.drawString(getTextInDestruction(), this.x, this.y - 10);
    }

    private String getTextInDestruction() {
        switch(this.type) {
               case LIVE : return "Won 1 live";
               case POINTS_25 : return "Won 25 points";
               case POINTS_50 : return "Won 50 points";
        }
        return "error type";
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
