package cl.talca.videogame;

import cl.talca.guieditor.actions.ListenerCreateCircle;
import cl.talca.videogame.component.Aircraft;
import cl.talca.videogame.component.GameKeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;

public class GameMainFrame extends JFrame {


    public GameMainFrame(String title) {
        System.out.println("Initializing game");

        this.setTitle(title);
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //print number of asteroid delete in the panel
        //GamePanel should have counter for the amount of astereoids destroyed
        GamePanel myGamePanel = new GamePanel();


        this.getContentPane().add(BorderLayout.NORTH, initActionPanel());
        this.getContentPane().add(BorderLayout.CENTER, myGamePanel);

        this.addKeyListener(new GameKeyListener(myGamePanel));


    }

    private JPanel initActionPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        return panel;
    }

}
