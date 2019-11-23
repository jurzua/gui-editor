package cl.talca.videogame.component;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Aircraft extends SuperShape implements ShapeInterface {

    private boolean inDestruction = false;
    int destructionIterations = 1;
    int MAX_DESTRUCTION_ITERATIONS = 60;
    private BufferedImage image = null;
    private JPanel observer = null;

    public Aircraft(int positionY, BufferedImage image, JPanel observer){
        super(400, positionY, 30, 30);
        this.image = image;
        this.observer = observer;
    }

    public void draw(Graphics g) {
        try {
            Graphics2D g2d = (Graphics2D) g;
            if(!inDestruction){
                g2d.drawImage(image, x, y, observer);
            } else {
                this.drawAircraftDestruction(g2d);
            }
        }catch (Exception ex){
            ex.printStackTrace();
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

    public void destroyYourself() {
        this.inDestruction = true;
    }

    public void updatePosition() {
        if(this.inDestruction){
            this.destructionIterations++;
        }
    }

    public Point getPosition(){
        return new Point(this.x , this.y);
    }

    public Boolean isVisible() {
        return !this.inDestruction || this.destructionIterations <= MAX_DESTRUCTION_ITERATIONS;
    }

    public void moveLeft() {
        this.x-=10;
    }

    public void moveRight() {
        this.x+=10;
    }
}
