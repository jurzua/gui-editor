package cl.talca.guieditor;

import cl.talca.guieditor.actions.ListenerCreateCircle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;

public class MainFrame extends JFrame {

    private ShapeArea shapeArea;

    public  MainFrame(String title) {
        System.out.println("Creating instance of MainFrame");

        this.setTitle(title);
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.shapeArea = new ShapeArea();

        this.getContentPane().add(BorderLayout.NORTH, initActionPanel());
        this.getContentPane().add(BorderLayout.SOUTH, initInfoPanel());
        this.getContentPane().add(BorderLayout.CENTER, this.shapeArea);

    }



    private JPanel initActionPanel() {
        JPanel panel = new JPanel();
        JButton buttonCreateCircle = new JButton("Create circle");
        JButton reset = new JButton("Reset");
        panel.add(buttonCreateCircle);
        panel.add(reset);
        panel.setBackground(Color.BLUE);
        buttonCreateCircle.addActionListener(new ListenerCreateCircle(this.shapeArea));
        return panel;
    }

    private JPanel initInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        return panel;
    }

}
