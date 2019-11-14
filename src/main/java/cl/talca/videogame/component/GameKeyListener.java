package cl.talca.videogame.component;

import cl.talca.videogame.GamePanel;

import javax.swing.*;
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
       try{
           if(keyEvent.getKeyCode() == 37) {
               //while apretado
               this.gamePanel.aircraftMoveLeft();
           }
           if(keyEvent.getKeyCode() == 39) {
               this.gamePanel.aircraftMoveRight();
           }
           if(keyEvent.getKeyCode() == 38){
               this.gamePanel.createBullet();
           }
       }catch(Exception e) {
           e.printStackTrace();
       }
    }

    public void keyReleased(KeyEvent keyEvent) {
    }
}
