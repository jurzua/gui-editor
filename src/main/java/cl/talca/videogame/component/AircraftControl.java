package cl.talca.videogame.component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AircraftControl implements KeyListener {


    private Aircraft aircraft;

    //Este método es un nuevo constructor que tiene como parametro una instancia de la clase aircraft
    //
    public AircraftControl(Aircraft aircraft){
        this.aircraft = aircraft;
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == 37) {
            this.aircraft.moveLeft();
        }
        if(keyEvent.getKeyCode() == 39) {
            this.aircraft.moveRight();
        }
    }
}
