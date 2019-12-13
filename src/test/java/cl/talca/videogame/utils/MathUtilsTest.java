package cl.talca.videogame.utils;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.awt.Point;

public class MathUtilsTest {

    @Test
    public void testRandomNumber() {

        //given
        int min = 5, max = 10;

        //when
        int result = MathUtils.randomNumber(min, max);

        Assert.assertThat(result, Matchers.greaterThanOrEqualTo(min));
        Assert.assertThat(result, Matchers.lessThanOrEqualTo(max));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomNumberForSameNumbers() {
        //given
        int min = 5, max = 5;

        //when
        MathUtils.randomNumber(min, max);
    }

    @Test
    public void testRectangleDoOverlap() {
        //case 1
        testRectangleDoOverlap0(new DoOverlapScenario(
                new Point(0, 0),
                new Point(10, 10),
                new Point(5, 0),
                new Point(15, 5),
                true));
    }

    private void testRectangleDoOverlap0(DoOverlapScenario scenario) {
        //when
        boolean result = MathUtils.doOverlap(scenario.sourcePoint1, scenario.sourcePoint2, scenario.targetPoint1, scenario.targetPoint2);

        //then
        Assert.assertThat(result, equalTo(scenario.expectedResult));

    }

    private class DoOverlapScenario {
        public Point sourcePoint1, sourcePoint2, targetPoint1, targetPoint2;
        public Boolean expectedResult;
        public DoOverlapScenario(Point sourcePoint1, Point sourcePoint2, Point targetPoint1, Point targetPoint2, Boolean expectedResult) {
            this.sourcePoint1 = sourcePoint1;
            this.sourcePoint2 = sourcePoint2;
            this.targetPoint1 = targetPoint1;
            this.targetPoint2 = targetPoint2;
            this.expectedResult = expectedResult;
        }
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
