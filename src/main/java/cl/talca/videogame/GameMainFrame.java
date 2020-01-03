package cl.talca.videogame;

import cl.talca.videogame.component.GameKeyListener;
import cl.talca.videogame.resources.GameStatistics;

import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;


public class GameMainFrame extends JFrame {


    public GameMainFrame(String title) throws IOException {
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
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
