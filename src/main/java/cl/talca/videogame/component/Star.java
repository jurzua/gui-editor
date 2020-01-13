package cl.talca.videogame.component;

import cl.talca.videogame.utils.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Star extends CollideShape implements ShapeInterface {
    private int speedY;
    private BufferedImage image = null;
    private JPanel observer = null;

    public Star(int screenWide, int screenHigh, int speedY, BufferedImage image, JPanel observer) {
        super(MathUtils.randomNumber(0,screenWide), MathUtils.randomNumber(0,screenHigh), 5, 5, 0, 0, screenHigh);
        this.image = image;
        this.observer = observer;
        this.speedY = speedY;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.draw(g2d);
    }

    private void draw(Graphics2D g2d){
        g2d.drawImage(image, x, y, observer);
    }

    public void updatePosition() {
        this.y += speedY;
    }

    public Boolean isVisible(){
        return this.y <= this.screenHigh;
    }
}
