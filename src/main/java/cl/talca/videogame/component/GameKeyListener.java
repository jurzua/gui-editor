package cl.talca.videogame.component;

import cl.talca.videogame.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    private GamePanel gamePanel;

    //Este método es un nuevo constructor que tiene como parametro una instancia de la clase aircraft
    //
    public GameKeyListener(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
        System.out.println(keyEvent.getKeyCode());
        if(keyEvent.getKeyCode() == 37) {
            this.gamePanel.getAircraft().moveLeft();
        }
        if(keyEvent.getKeyCode() == 39) {
            this.gamePanel.getAircraft().moveRight();
        }
        if(keyEvent.getKeyCode() == 38){
            this.gamePanel.createBullet();
        }
    }
}
