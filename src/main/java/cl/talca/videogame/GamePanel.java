package cl.talca.videogame;

import cl.talca.videogame.component.Asteroid;
import cl.talca.videogame.component.Shape;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    static int SCREEN_WIDE = 800;

    List<Shape> shapeList = new ArrayList<>();

    private Timer timer = new Timer(10, this);
    double x = 0, y = 0, vx = 2, vy = 2;

    public GamePanel() {
        setBackground(Color.LIGHT_GRAY);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        shapeList.add(new Asteroid(400, 1));

        for(Shape shape : shapeList) {
            shape.draw(g);
        }

        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(Shape shape : shapeList) {
            shape.updatePosition();
        }

        this.repaint();
    }
}
