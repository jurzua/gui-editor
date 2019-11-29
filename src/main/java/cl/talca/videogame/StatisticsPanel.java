package cl.talca.videogame;

import cl.talca.videogame.resources.GameStatistics;

import javax.swing.*;

public class StatisticsPanel extends JPanel {

    private GameStatistics gameStatistics = new GameStatistics();

    public void showCount(){
        this.add(gameStatistics.showLiveCount());
        this.add(gameStatistics.showAsteroidPoints());
    }

    private void add(int i) {
    }

}
