package cl.talca.videogame.component;

import java.awt.Graphics;

public interface ShapeInterface {

    Integer x = 0;
    Integer y = 0;
    void draw(Graphics g);
    void updatePosition();
    Boolean isVisible();
}
