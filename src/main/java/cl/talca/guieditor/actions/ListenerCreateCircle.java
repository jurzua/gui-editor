package cl.talca.guieditor.actions;

import cl.talca.guieditor.ShapeArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerCreateCircle implements ActionListener {

    private ShapeArea shapeArea;

    public ListenerCreateCircle (ShapeArea shapeArea){
        this.shapeArea = shapeArea;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.shapeArea.createCircle();
    }


}
