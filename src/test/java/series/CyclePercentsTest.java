package series;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class CyclePercentsTest {

    @Test
    public void test() {
        List<Double> input = Arrays.asList(12., 8., 121., 81., 1.22, .82);
        List<Double> trend = Arrays.asList(10., 10., 100., 100., 1., 1.);
        double[] percents = Series.cyclePercents(input, trend).stream()
                .mapToDouble(v -> v)
                .toArray();
        assertArrayEquals(new double[]{20., -20., 21., -19., 22., -18.}, percents, 0.01);
    }
}
