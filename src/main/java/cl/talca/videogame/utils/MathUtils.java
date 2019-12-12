package cl.talca.videogame.utils;

import java.awt.Point;

public class MathUtils {

    public static boolean doOverlap(Point sourcePoint1, Point sourcePoint2, Point targetPoint1, Point targetPoint2) {
        if (sourcePoint1.x > targetPoint2.x || targetPoint1.x > sourcePoint2.x) {
            return false;
        }

        /*
        la siguiente linea es el problema. En el eje X _todo funciona bien, sin embargo
        en el eje Y la condición esta al revés
        /linea original: "if (sourcePoint1.y < targetPoint2.y || targetPoint1.y < sourcePoint2.y) {"
         */
        if (sourcePoint1.y > targetPoint2.y || targetPoint1.y > sourcePoint2.y) {
            return false;
        }
        return true;
    }
}
