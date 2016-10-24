package series;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AverageTest {
    @Test
    public void test() {
        assertEquals(3.35, Series.average(Arrays.asList(2., 3., 4., 5.), Arrays.asList(1., .9, .8, .7)), .001);
    }
}
