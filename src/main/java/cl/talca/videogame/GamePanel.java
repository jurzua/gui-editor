package cl.talca.videogame;

import cl.talca.videogame.component.Aircraft;
import cl.talca.videogame.component.Asteroid;
import cl.talca.videogame.component.Shape;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    static int SCREEN_WIDE = 600;
    static int SCREEN_HIGH = 400;

    List<Shape> shapeList = new ArrayList<Shape>();

    private Timer timer = new Timer(5, this);
    double x = 0, y = 0, vx = 2, vy = 2;
    int asteroid = 10;

    public GamePanel() {

        for(int index=0;index<asteroid;index++){
            shapeList.add(new Asteroid(SCREEN_WIDE, SCREEN_HIGH, MathHelper.randomNumber(1,3)));
        }
        setBackground(Color.LIGHT_GRAY);

        shapeList.add(new Aircraft(SCREEN_HIGH -10));
        this.addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(Shape shape : shapeList) {
            shape.draw(g);
        }
        this.timer.start();
    }

    public void actionPerformed(ActionEvent e) {

        List<Shape> listToDelete = new ArrayList<Shape>();
        for(Shape shape : shapeList) {
            shape.updatePosition();
            if(!shape.isVisible()){
                listToDelete.add(shape);
            }
        }

        for(Shape shapeToDelete : listToDelete) {
            shapeList.remove(shapeToDelete);
            shapeList.add(new Asteroid(SCREEN_WIDE, SCREEN_HIGH, MathHelper.randomNumber(1,3)));
        }
        this.repaint();
    }

    public void keyTyped(KeyEvent keyEvent) {
        System.out.println(String.format("AAAAcode=%s -- char=%s", keyEvent.getKeyCode(), keyEvent.getKeyChar()));
    }

    public void keyPressed(KeyEvent keyEvent) {
        System.out.println(String.format("BBBcode=%s -- char=%s", keyEvent.getKeyCode(), keyEvent.getKeyChar()));

    }

    public void keyReleased(KeyEvent keyEvent) {
        System.out.println(String.format("CCCCcode=%s -- char=%s", keyEvent.getKeyCode(), keyEvent.getKeyChar()));

    }
}
