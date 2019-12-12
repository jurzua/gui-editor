package cl.talca.videogame.utils;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import java.awt.Point;

public class MathUtilsTest {

    @Test
    public void testRectangleDoOverlapCase1() {
        //given
        Point sourcePoint1 = new Point(0, 0), sourcePoint2 = new Point(10, 10),
                targetPoint1 = new Point(5, 0),targetPoint2 = new Point(15, 5);

        //when
        boolean result = MathUtils.doOverlap(sourcePoint1, sourcePoint2, targetPoint1, targetPoint2);

        //then
        Assert.assertThat(result, equalTo(true));
    }

    @Test
    public void testRectangleDoOverlapCase2() {
        //given
        Point sourcePoint1 = new Point(0, 0), sourcePoint2 = new Point(10, 10),
                targetPoint1 = new Point(11, 11),targetPoint2 = new Point(15, 5);

        //when
        boolean result = MathUtils.doOverlap(sourcePoint1, sourcePoint2, targetPoint1, targetPoint2);

        //then
        Assert.assertThat(result, equalTo(false));
    }

    @Test
    public void testRectangleDoOverlapCase3() {
        //given
        Point sourcePoint1 = new Point(0, 0), sourcePoint2 = new Point(10, 10),
                targetPoint1 = new Point(-1, -1),targetPoint2 = new Point(15, 5);

        //when
        boolean result = MathUtils.doOverlap(sourcePoint1, sourcePoint2, targetPoint1, targetPoint2);

        //then
        Assert.assertThat(result, equalTo(true));
    }


}
