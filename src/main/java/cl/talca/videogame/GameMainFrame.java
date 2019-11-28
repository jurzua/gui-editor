package cl.talca.videogame;

import cl.talca.videogame.component.GameKeyListener;
import cl.talca.videogame.resources.GameStatistics;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;


public class GameMainFrame extends JFrame {

    private GameStatistics gameStatistics = new GameStatistics();

    public GameMainFrame(String title) {
        System.out.println("Initializing game");

        this.setTitle(title);
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //print number of asteroid delete in the panel
        //GamePanel should have counter for the amount of astereoids destroyed
        GamePanel myGamePanel = new GamePanel();

        this.getContentPane().add(BorderLayout.NORTH, panelStatistics());
        this.getContentPane().add(BorderLayout.CENTER, myGamePanel);
        this.addKeyListener(new GameKeyListener(myGamePanel));
    }

    private JPanel panelStatistics() {
        JPanel panel = new JPanel();
        JButton showLivesCount = new JButton(String.valueOf(this.gameStatistics.showLiveCount()));
        panel.add(showLivesCount);
        JButton showAsteroidDestroyed = new JButton(String.valueOf(this.gameStatistics.showAsteroidPoints()));
        panel.add(showAsteroidDestroyed);
        panel.setBackground(Color.BLUE);
        return panel;
    }

}
