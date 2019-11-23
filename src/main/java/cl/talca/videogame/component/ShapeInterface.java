package cl.talca.videogame.component;

import java.awt.Graphics;

public interface ShapeInterface {
    void draw(Graphics g);
    void updatePosition();
    Boolean isVisible();
}
