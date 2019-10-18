package cl.talca.videogame.component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AircraftControl implements KeyListener {

    public void keyTyped(KeyEvent keyEvent) {
    }

    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == 37) {
            System.out.println("Move left");
            
        }
        if(keyEvent.getKeyCode() == 39) {
            System.out.println("Move right");
        }
    }
}
