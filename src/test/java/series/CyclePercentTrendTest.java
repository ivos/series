package series;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class CyclePercentTrendTest {
    @Test
    public void test() {
        List<Double> cycle = Series.cyclePercentTrend(
                Arrays.asList(2., 1., 5., 4., 10., 6., 9., 8., 11.),
                Arrays.asList(2., 3., 4., 5., 6., 7., 8., 9., 10.), 2, 2, distance -> (1 - 0.1 * distance));
        List<Double> expected =
                Arrays.asList(28.09, -35.59, 30.36, -33.16, 35.86, -15.1, 29.11, -14.8, 27.62);
        assertArrayEquals(expected.toArray(), cycle.toArray());
    }
}
