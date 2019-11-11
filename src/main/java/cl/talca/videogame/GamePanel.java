package cl.talca.videogame;

import cl.talca.videogame.component.Aircraft;
import cl.talca.videogame.component.Asteroid;
import cl.talca.videogame.component.Bullet;
import cl.talca.videogame.component.Shape;
import sun.java2d.pipe.BufferedTextPipe;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    static int SCREEN_WIDE = 600;
    static int SCREEN_HIGH = 400;

    List<Shape> shapeList = new ArrayList<Shape>();
    Aircraft aircraft;

    private Timer timer = new Timer(5, this);
    double x = 0, y = 0, vx = 2, vy = 2;
    int asteroid = 8;

    public GamePanel() {

        for(int index=0;index<asteroid;index++){
            shapeList.add(new Asteroid(SCREEN_WIDE, SCREEN_HIGH, MathHelper.randomNumber(1,2)));
        }
        setBackground(Color.LIGHT_GRAY);
        this.aircraft = new Aircraft(SCREEN_HIGH -10);
        shapeList.add(this.aircraft);
    }

    public void createBullet(){
        //String aa = "Hola mundo";
        Point initialPosition = this.aircraft.getPosition();
        Bullet myBullet = new Bullet(initialPosition.x, initialPosition.y);
        shapeList.add(myBullet);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(Shape shape : shapeList) {
            shape.draw(g);
        }
        this.timer.start();
    }

    //call with timer
    public void actionPerformed(ActionEvent e) {

        for(Bullet bullet : getBullets()){
            //each bullet compare with all asteroid's position
            for(Asteroid asteroid : getAsteroids()){
                if(bullet.destroyAsteroid(asteroid)){
                    asteroid.destroyed();
                    break;
                }
            }
        }

        //comparison of the aircraft with each asteroid
        for(Asteroid asteroid : getAsteroids()){
            if(aircraft.destroyAsteroid(asteroid)){
                asteroid.destroyed();
                break;
            }
        }

        List<Shape> listToDelete = new ArrayList<Shape>();
        for(Shape shape : shapeList) {

            shape.updatePosition();
            if(!shape.isVisible()){
                listToDelete.add(shape);
            }
        }
        for(Shape shapeToDelete : listToDelete) {
            shapeList.remove(shapeToDelete);
            if(shapeToDelete instanceof Asteroid){
                shapeList.add(new Asteroid(SCREEN_WIDE, SCREEN_HIGH, MathHelper.randomNumber(1,2)));
            }
        }

        this.repaint();
        this.printState();
    }

    private void printState() {
        System.out.println(String.format("Asteroids = %d, Bullets = %d", this.getAsteroids().size(), this.getBullets().size()));
    }

    private List<Asteroid> getAsteroids(){
        List<Asteroid> list = new ArrayList<Asteroid>();
        for(Shape shape : this.shapeList){
            if(shape instanceof Asteroid){
                list.add((Asteroid) shape);
            }
        }
        return list;
    }

    private List<Bullet> getBullets() {
        List<Bullet> list = new ArrayList<Bullet>();
        for(Shape shape : this.shapeList) {
            if(shape instanceof Bullet) {
                list.add((Bullet)shape);
            }
        }
        return list;
    }

    public Aircraft getAircraft(){
        return this.aircraft;
    }

}
