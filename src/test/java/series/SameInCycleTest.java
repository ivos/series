package series;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class SameInCycleTest {

    @Test
    public void test() {
        List<List<Double>> actual = Series.sameInCycle(
                Arrays.asList(1., 11., 21., 2., 12., 22., 3., 13., 23., 4., 14.), 3);
        List<List<Double>> expected = Arrays.asList(
                Arrays.asList(1., 2., 3., 4.),
                Arrays.asList(11., 12., 13., 14.),
                Arrays.asList(21., 22., 23.)
        );
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
