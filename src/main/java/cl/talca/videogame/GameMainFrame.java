package cl.talca.videogame;

import cl.talca.guieditor.ShapeArea;
import cl.talca.videogame.component.Aircraft;
import cl.talca.videogame.component.AircraftControl;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class GameMainFrame extends JFrame {


    public GameMainFrame(String title) {
        System.out.println("Initializing game");

        this.setTitle(title);
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //The AircraftControl neesita como parametro una instancia de la clase Aircraft (el avion)
        //El avion es creado en la clase GamePanel
        //GamePanel necesita un metodo que puedes llamar getAircraft que retorne el avion. "public Aircraft getAircraft()..."
        //ese avion lo tienes que guardar en una variable y entregarselo al contructor de AircraftControl
        // en la linea "this.addKeyListener(new AircraftControl(null));"
        // eso es todo

        GamePanel myGamePanel = new GamePanel();
        Aircraft avion = myGamePanel.getAircraft();
        this.getContentPane().add(myGamePanel);

        //cuando creamos el constructor en AircraftControl, necesitamos una instancia del objeto Aircraft.
        //Si vez la siguiente linea, no hay ningun parametro, por eso el error.
        //Ahora voy a poner ahí un null para que puedas correr el programa

        this.addKeyListener(new AircraftControl(avion));


    }

}
