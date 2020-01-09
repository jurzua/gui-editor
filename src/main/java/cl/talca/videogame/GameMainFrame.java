package cl.talca.videogame;

import cl.talca.videogame.component.GameKeyListener;
import cl.talca.videogame.resources.GameStatistics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GameMainFrame extends JFrame {


    public GameMainFrame(String title) throws IOException {
        int i;
        do{
            final JFrame parent = new JFrame();
            i = 1; //this int is only for the condition of finished

            String name = JOptionPane.showInputDialog(parent,
                    "What is your name?", null);
            String password = JOptionPane.showInputDialog(parent,
                    "What is your password?", null);
            String testing = JOptionPane.showInputDialog(parent, "Test", null, i);
            i = i++;

        }while(i == 1);

        System.out.println("Initializing game");

        //print number of asteroid delete in the panel
        //GamePanel should have counter for the amount of astereoids destroyed
        GameStatistics gameStatistics = new GameStatistics();
        StatisticsPanel statisticsPanel = new StatisticsPanel(gameStatistics);
        GamePanel myGamePanel = new GamePanel(gameStatistics, statisticsPanel);

        this.getContentPane().add(BorderLayout.NORTH, statisticsPanel);
        this.getContentPane().add(BorderLayout.CENTER, myGamePanel);
        this.addKeyListener(new GameKeyListener(myGamePanel));

        this.setTitle(title);
        this.pack();
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
