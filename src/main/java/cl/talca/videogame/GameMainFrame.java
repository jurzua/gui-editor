package cl.talca.videogame;

import cl.talca.videogame.component.Aircraft;
import cl.talca.videogame.component.GameKeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameMainFrame extends JFrame {


    public GameMainFrame(String title) {
        System.out.println("Initializing game");

        this.setTitle(title);
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        GamePanel myGamePanel = new GamePanel();
        this.getContentPane().add(myGamePanel);

        this.addKeyListener(new GameKeyListener(myGamePanel));


    }

}
