package series;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class TrendTest {
    @Test
    public void test() {
        List<Double> trend = Series.trend(Arrays.asList(2., 1., 5., 4., 7., 6., 9.), 4, distance -> (1 - 0.1 * distance));
        List<Double> expected = Arrays.asList(3.48, 3.58, 3.8, 4.64, 6.18, 6.3, 6.45, 6.6, 6.75, 6.9, 7.05);
        assertArrayEquals(expected.toArray(), trend.toArray());
    }

    @Test
    public void shortData() {
        List<Double> trend = Series.trend(Arrays.asList(2., 1., 5.), 4, distance -> (1 - 0.1 * distance));
        List<Double> expected = Arrays.asList(2.56, 2.61, 2.78, 2.95, 3.12, 3.29, 3.46);
        assertArrayEquals(expected.toArray(), trend.toArray());
    }

    @Test
    public void shortDataCheck() {
        List<Double> trend = Series.trend(Arrays.asList(2., 1., 5.), 3, distance -> (1 - 0.1 * distance));
        List<Double> expected = Arrays.asList(2.56, 2.61, 2.78, 2.95, 3.12, 3.29);
        assertArrayEquals(expected.toArray(), trend.toArray());
    }

    @Test
    public void singleValueData() {
        List<Double> trend = Series.trend(Arrays.asList(2.), 3, distance -> (1 - 0.1 * distance));
        List<Double> expected = Arrays.asList(2., 2., 2., 2.);
        assertArrayEquals(expected.toArray(), trend.toArray());
    }

    @Test
    public void emptyData() {
        List<Double> trend = Series.trend(Arrays.asList(), 3, distance -> (1 - 0.1 * distance));
        List<Double> expected = Arrays.asList();
        assertArrayEquals(expected.toArray(), trend.toArray());
    }
}
