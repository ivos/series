package series;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TrendIndexTest {

    @Test
    public void trendIndex_Within() {
        assertEquals(5, (int) Series.trendIndex(5, 20, 10, 0));
        assertEquals(4, (int) Series.trendIndex(5, 20, 10, 1));
        assertEquals(6, (int) Series.trendIndex(5, 20, 10, 2));
        assertEquals(3, (int) Series.trendIndex(5, 20, 10, 3));
        assertEquals(7, (int) Series.trendIndex(5, 20, 10, 4));
        assertEquals(2, (int) Series.trendIndex(5, 20, 10, 5));
        assertEquals(8, (int) Series.trendIndex(5, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapStart0() {
        assertEquals(0, (int) Series.trendIndex(0, 20, 10, 0));
        assertEquals(6, (int) Series.trendIndex(0, 20, 10, 1));
        assertEquals(1, (int) Series.trendIndex(0, 20, 10, 2));
        assertEquals(7, (int) Series.trendIndex(0, 20, 10, 3));
        assertEquals(2, (int) Series.trendIndex(0, 20, 10, 4));
        assertEquals(8, (int) Series.trendIndex(0, 20, 10, 5));
        assertEquals(3, (int) Series.trendIndex(0, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapStart1() {
        assertEquals(1, (int) Series.trendIndex(1, 20, 10, 0));
        assertEquals(0, (int) Series.trendIndex(1, 20, 10, 1));
        assertEquals(2, (int) Series.trendIndex(1, 20, 10, 2));
        assertEquals(7, (int) Series.trendIndex(1, 20, 10, 3));
        assertEquals(3, (int) Series.trendIndex(1, 20, 10, 4));
        assertEquals(8, (int) Series.trendIndex(1, 20, 10, 5));
        assertEquals(4, (int) Series.trendIndex(1, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapStart2() {
        assertEquals(2, (int) Series.trendIndex(2, 20, 10, 0));
        assertEquals(1, (int) Series.trendIndex(2, 20, 10, 1));
        assertEquals(3, (int) Series.trendIndex(2, 20, 10, 2));
        assertEquals(0, (int) Series.trendIndex(2, 20, 10, 3));
        assertEquals(4, (int) Series.trendIndex(2, 20, 10, 4));
        assertEquals(8, (int) Series.trendIndex(2, 20, 10, 5));
        assertEquals(5, (int) Series.trendIndex(2, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapEndLast() {
        assertEquals(19, (int) Series.trendIndex(19, 20, 10, 0));
        assertEquals(18, (int) Series.trendIndex(19, 20, 10, 1));
        assertEquals(13, (int) Series.trendIndex(19, 20, 10, 2));
        assertEquals(17, (int) Series.trendIndex(19, 20, 10, 3));
        assertEquals(12, (int) Series.trendIndex(19, 20, 10, 4));
        assertEquals(16, (int) Series.trendIndex(19, 20, 10, 5));
        assertEquals(11, (int) Series.trendIndex(19, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapEndLastMinus1() {
        assertEquals(18, (int) Series.trendIndex(18, 20, 10, 0));
        assertEquals(17, (int) Series.trendIndex(18, 20, 10, 1));
        assertEquals(19, (int) Series.trendIndex(18, 20, 10, 2));
        assertEquals(16, (int) Series.trendIndex(18, 20, 10, 3));
        assertEquals(12, (int) Series.trendIndex(18, 20, 10, 4));
        assertEquals(15, (int) Series.trendIndex(18, 20, 10, 5));
        assertEquals(11, (int) Series.trendIndex(18, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapEndLastMinus2() {
        assertEquals(17, (int) Series.trendIndex(17, 20, 10, 0));
        assertEquals(16, (int) Series.trendIndex(17, 20, 10, 1));
        assertEquals(18, (int) Series.trendIndex(17, 20, 10, 2));
        assertEquals(15, (int) Series.trendIndex(17, 20, 10, 3));
        assertEquals(19, (int) Series.trendIndex(17, 20, 10, 4));
        assertEquals(14, (int) Series.trendIndex(17, 20, 10, 5));
        assertEquals(11, (int) Series.trendIndex(17, 20, 10, 6));
    }

    @Test
    public void trendIndex_CycleLengthEqSize() {
        assertEquals(0, (int) Series.trendIndex(0, 4, 4, 0));
        assertEquals(3, (int) Series.trendIndex(0, 4, 4, 1));
        assertEquals(1, (int) Series.trendIndex(0, 4, 4, 2));
        assertNull(Series.trendIndex(0, 4, 4, 3));
    }
}
