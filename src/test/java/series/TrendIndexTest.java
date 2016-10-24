package series;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrendIndexTest {

    @Test
    public void trendIndex_Within() {
        assertEquals(5, Series.trendIndex(5, 20, 10, 0));
        assertEquals(4, Series.trendIndex(5, 20, 10, 1));
        assertEquals(6, Series.trendIndex(5, 20, 10, 2));
        assertEquals(3, Series.trendIndex(5, 20, 10, 3));
        assertEquals(7, Series.trendIndex(5, 20, 10, 4));
        assertEquals(2, Series.trendIndex(5, 20, 10, 5));
        assertEquals(8, Series.trendIndex(5, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapStart0() {
        assertEquals(0, Series.trendIndex(0, 20, 10, 0));
        assertEquals(6, Series.trendIndex(0, 20, 10, 1));
        assertEquals(1, Series.trendIndex(0, 20, 10, 2));
        assertEquals(7, Series.trendIndex(0, 20, 10, 3));
        assertEquals(2, Series.trendIndex(0, 20, 10, 4));
        assertEquals(8, Series.trendIndex(0, 20, 10, 5));
        assertEquals(3, Series.trendIndex(0, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapStart1() {
        assertEquals(1, Series.trendIndex(1, 20, 10, 0));
        assertEquals(0, Series.trendIndex(1, 20, 10, 1));
        assertEquals(2, Series.trendIndex(1, 20, 10, 2));
        assertEquals(7, Series.trendIndex(1, 20, 10, 3));
        assertEquals(3, Series.trendIndex(1, 20, 10, 4));
        assertEquals(8, Series.trendIndex(1, 20, 10, 5));
        assertEquals(4, Series.trendIndex(1, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapStart2() {
        assertEquals(2, Series.trendIndex(2, 20, 10, 0));
        assertEquals(1, Series.trendIndex(2, 20, 10, 1));
        assertEquals(3, Series.trendIndex(2, 20, 10, 2));
        assertEquals(0, Series.trendIndex(2, 20, 10, 3));
        assertEquals(4, Series.trendIndex(2, 20, 10, 4));
        assertEquals(8, Series.trendIndex(2, 20, 10, 5));
        assertEquals(5, Series.trendIndex(2, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapEndLast() {
        assertEquals(19, Series.trendIndex(19, 20, 10, 0));
        assertEquals(18, Series.trendIndex(19, 20, 10, 1));
        assertEquals(13, Series.trendIndex(19, 20, 10, 2));
        assertEquals(17, Series.trendIndex(19, 20, 10, 3));
        assertEquals(12, Series.trendIndex(19, 20, 10, 4));
        assertEquals(16, Series.trendIndex(19, 20, 10, 5));
        assertEquals(11, Series.trendIndex(19, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapEndLastMinus1() {
        assertEquals(18, Series.trendIndex(18, 20, 10, 0));
        assertEquals(17, Series.trendIndex(18, 20, 10, 1));
        assertEquals(19, Series.trendIndex(18, 20, 10, 2));
        assertEquals(16, Series.trendIndex(18, 20, 10, 3));
        assertEquals(12, Series.trendIndex(18, 20, 10, 4));
        assertEquals(15, Series.trendIndex(18, 20, 10, 5));
        assertEquals(11, Series.trendIndex(18, 20, 10, 6));
    }

    @Test
    public void trendIndex_WrapEndLastMinus2() {
        assertEquals(17, Series.trendIndex(17, 20, 10, 0));
        assertEquals(16, Series.trendIndex(17, 20, 10, 1));
        assertEquals(18, Series.trendIndex(17, 20, 10, 2));
        assertEquals(15, Series.trendIndex(17, 20, 10, 3));
        assertEquals(19, Series.trendIndex(17, 20, 10, 4));
        assertEquals(14, Series.trendIndex(17, 20, 10, 5));
        assertEquals(11, Series.trendIndex(17, 20, 10, 6));
    }

    @Test
    public void trendIndex_CycleLengthEqSize() {
        assertEquals(0, Series.trendIndex(0, 4, 4, 0));
        assertEquals(3, Series.trendIndex(0, 4, 4, 1));
        assertEquals(1, Series.trendIndex(0, 4, 4, 2));
        try {
            Series.trendIndex(0, 4, 4, 3);
            fail("Should throw.");
        } catch (RuntimeException e) {
            assertEquals("Cannot wrap trend index, cycle length must be less than series length.", e.getMessage());
        }
    }
}
