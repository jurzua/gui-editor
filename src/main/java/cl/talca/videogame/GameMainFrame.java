package cl.talca.videogame;

import cl.talca.guieditor.ShapeArea;
import cl.talca.videogame.component.AircraftControl;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class GameMainFrame extends JFrame {


    public GameMainFrame(String title) {
        System.out.println("Initializing game");

        this.setTitle(title);
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new AircraftControl());
        this.getContentPane().add(new GamePanel());

    }

}
