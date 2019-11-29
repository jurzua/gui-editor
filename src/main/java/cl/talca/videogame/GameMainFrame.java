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

    /**
     * Hola pesao. No entendiste lo que te escribí. Lo voy a copiar desde Whatsapp:
     *
     * "En este código tu creas una instancia de la clase JPanel. Sin embargo, lo que yo te digo (toma como ejemplo la clase GamePanel) es que tienes que crear una nueva clase
     * llamada "StatisticsPanel" y que extienda (extends) la clase JPanel. Luego el método panelStatistics tiene que crear una instancia de esta nueva clase y retornarla"
     *
     * Lo que haces aquí es un método llamado "panelStatistics", pero yo te pido que crees una nueva clase llamada "StatisticsPanel".
     * Y esa clase tiene que extender "JPanel"
     *
     * Por favor sigue estos pasos:
     * 1. Crea una clase llamada "StatisticsPanel" que extienda "JPanel"
     * 2. agrega una propiedad privada en StatisticsPanel de tipo "GameStatistics"
     * 3. crea un constructor en la clase StatisticsPanel que reciba como parametro una instancia de GameStatistics
     * 4. en el constructor inicializa el panel con color azul y inicializa la propiedad global de tipo GameStatistics
     * 5. crea un método publico en StatisticsPanel llamada "update" sin contenido por el momento
     *
     */
    private JPanel panelStatistics() {
        StatisticsPanel panel = new StatisticsPanel();
        JButton showLivesCount = new JButton(String.valueOf(this.gameStatistics.showLiveCount()));
        panel.add(showLivesCount);
        JButton showAsteroidDestroyed = new JButton(String.valueOf(this.gameStatistics.showAsteroidPoints()));
        panel.add(showAsteroidDestroyed);
        panel.setBackground(Color.BLUE);
        return panel;
    }

}
