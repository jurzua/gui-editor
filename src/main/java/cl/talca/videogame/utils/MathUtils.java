package cl.talca.videogame.utils;

import java.awt.Point;
import java.util.Random;

public class MathUtils {

    public static boolean doOverlap(Point sourcePoint1, Point sourcePoint2, Point targetPoint1, Point targetPoint2) {
        if (sourcePoint1.x > targetPoint2.x || targetPoint1.x > sourcePoint2.x) {
            return false;
        }

        if (sourcePoint1.y > targetPoint2.y || targetPoint1.y > sourcePoint2.y) {
            return false;
        }
        return true;
    }

    public static int randomNumber(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
