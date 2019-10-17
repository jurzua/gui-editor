package cl.talca.guieditor;

import cl.talca.guieditor.shapes.Circle;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class ShapeArea extends JPanel {

    public ShapeArea() {
        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //add my shapes


        Circle circle = new Circle(10, 20);
        circle.draw(g);
    }

}
