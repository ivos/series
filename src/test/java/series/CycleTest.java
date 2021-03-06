package series;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class CycleTest {
    @Test
    public void test() {
        List<Double> cycle = Series.cycle(
                Arrays.asList(2., 1., 5., 4., 10., 6., 9., 8., 11.),
                Arrays.asList(2., 3., 4., 5., 6., 7., 8., 9., 10., 11., 12.), 2, 2, distance -> (1 - 0.1 * distance));
        List<Double> expected =
                Arrays.asList(2.56, 1.93, 5.21, 3.34, 8.15, 5.94, 10.33, 7.67, 12.76, 9.37, 15.31);
        assertArrayEquals(expected.toArray(), cycle.toArray());
    }

    @Test
    public void shortData() {
        List<Double> cycle = Series.cycle(
                Arrays.asList(2., 1., 5.),
                Arrays.asList(2., 3., 4., 11., 12.), 2, 2, distance -> (1 - 0.1 * distance));
        List<Double> expected =
                Arrays.asList(2.24, 1., 4.53, 3.67, 13.58);
        assertArrayEquals(expected.toArray(), cycle.toArray());
    }

    @Test
    public void cycleLengthData() {
        List<Double> cycle = Series.cycle(
                Arrays.asList(2., 3.),
                Arrays.asList(2., 3., 4., 5.), 2, 2, distance -> (1 - 0.1 * distance));
        List<Double> expected =
                Arrays.asList(2., 3., 4., 5.);
        assertArrayEquals(expected.toArray(), cycle.toArray());
    }

    @Test
    public void singleValueData() {
        List<Double> cycle = Series.cycle(
                Arrays.asList(2.),
                Arrays.asList(2., 3., 4.), 2, 2, distance -> (1 - 0.1 * distance));
        List<Double> expected =
                Arrays.asList(2., 3., 4.);
        assertArrayEquals(expected.toArray(), cycle.toArray());
    }

    @Test
    public void emptyData() {
        List<Double> cycle = Series.cycle(
                Arrays.asList(),
                Arrays.asList(3., 4.), 2, 2, distance -> (1 - 0.1 * distance));
        List<Double> expected =
                Arrays.asList();
        assertArrayEquals(expected.toArray(), cycle.toArray());
    }
}
