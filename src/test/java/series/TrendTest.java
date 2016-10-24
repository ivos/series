package series;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class TrendTest {
    @Test
    public void test() {
        List<Double> trend = Series.trend(Arrays.asList(2., 1., 5., 4., 7., 6., 9.), 4, distance -> (1 - 0.1 * distance));
        List<Double> expected = Arrays.asList(3.48, 3.58, 3.8, 4.64, 6.18, 6.3, 6.45);
        assertArrayEquals(expected.toArray(), trend.toArray());
    }
}