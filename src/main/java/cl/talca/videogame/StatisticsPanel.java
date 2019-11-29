package cl.talca.videogame;

import cl.talca.videogame.resources.GameStatistics;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    private GameStatistics gameStatistics;
    private JLabel scoreLabel;
    private JLabel livesLabel;

    /**
     * 1. Crea una clase llamada "StatisticsPanel" que extienda "JPanel"
     * 2. agrega una propiedad privada en StatisticsPanel de tipo "GameStatistics"
     * 3. crea un constructor en la clase StatisticsPanel que reciba como parametro una instancia de GameStatistics
     * 4. en el constructor inicializa el panel con color azul
     * 5. inicializa la propiedad global de tipo GameStatistics con el input del constructor
     * 6. crea un método publico en StatisticsPanel llamada "update" sin contenido por el momento
     *
     */


    public StatisticsPanel (GameStatistics gameStatistics){
        this.gameStatistics = gameStatistics;
        this.setBackground(Color.YELLOW);

        this.setLayout(new FlowLayout());
        this.add(this.scoreLabel = new JLabel("Score: XXX"));
        this.add(this.livesLabel = new JLabel("Lives: XXX"));
    }

    //JButton showLivesCount = new JButton(String.valueOf(this.gameStatistics.showLiveCount()));
    //panel.add(showLivesCount);

    public void update(){
        this.livesLabel.setText("Lives: " + this.gameStatistics.getLives());
        this.scoreLabel.setText("Score: " + this.gameStatistics.getScore());

        //System.out.println(this.gameStatistics.showAsteroidPoints());
    }
}
