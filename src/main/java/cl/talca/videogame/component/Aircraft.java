package cl.talca.videogame.component;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Aircraft implements ShapeInterface {

    int x=400, y, width=30,height=30;
    boolean safe = true;
    int destructionIterations = 1;
    int MAX_DESTRUCTION_ITERATIONS = 60;
    private BufferedImage image = null;
    private JPanel observer = null;

    public Aircraft(int positionY, BufferedImage image, JPanel observer){
        this.y = positionY;
        this.image = image;
        this.observer = observer;
    }

    public Point getP1(){
        return new Point(this.x, this.y);
    }

    public Point getP2(){
        return new Point(this.x + this.width, this.y + this.height);
    }


    public void draw(Graphics g) {
        try {
            Graphics2D g2d = (Graphics2D) g;
            if(this.safe == true){
                //g2d.drawImage(image, x, y, observer);
                System.out.println();
                //g2d.setColor(Color.BLACK);
                //g.drawRect(x, y, width, height);
                //g2d.fillRect(x, y, width, height);
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
