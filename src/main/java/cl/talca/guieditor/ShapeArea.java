package cl.talca.guieditor;

import cl.talca.guieditor.shapes.Circle;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ShapeArea extends JPanel {

    //in shapeArea: property list circle, initialization in constructor, print in paint component (draw)

    private List<Circle> circles;

    public ShapeArea() {
        this.setBackground(Color.WHITE);
        this.circles = new ArrayList<Circle>();

        this.circles.add(new Circle(50, 100, Color.YELLOW));
        this.circles.add(new Circle(150, 110, Color.RED));
    }

    public void createCircle(){
        System.out.println("Estoy creando un circulo");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //add my shapes
        //Color color = Color.CYAN;

        //Circle circle = new Circle(10, 20, color);
        // for (Type variable : list)
        for(Circle aCircle  : circles){
            aCircle.draw(g);
        }

    }

}
