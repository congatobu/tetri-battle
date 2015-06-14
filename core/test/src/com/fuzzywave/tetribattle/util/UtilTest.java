package com.fuzzywave.tetribattle.util;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class UtilTest {

    @Test
    public void testOverlapsExactlyTheSame() throws Exception {
        assertTrue(Util.overlaps(2, 0, 2, 4, 2, 0, 2, 4));
    }

    @Test
    public void testOverlapsMuchMoreBigger() throws Exception {
        assertTrue(Util.overlaps(0, 0, 4, 4, 1, 1, 2, 2));
    }

    @Test
    public void testOverlapsSlightlyBigger() throws Exception {
        assertTrue(Util.overlaps(2, 0, 2, 4, 2, 0, 2, 3));
    }

    @Test
    public void testOverlaps() throws Exception {
        assertTrue(Util.overlaps(2, 0, 2, 3, 2, 0, 4, 2));
    }

    @Test
    public void testNotOverlaps() throws Exception {
        assertFalse(Util.overlaps(2, 0, 2, 3, 4, 0, 2, 2));
    }

    @Test
    public void testContainsExactlyTheSame() throws Exception {
        assertTrue(Util.contains(2, 0, 2, 4, 2, 0, 2, 4));
    }

    @Test
    public void testContainsMuchMoreBigger() throws Exception {
        assertTrue(Util.contains(0, 0, 4, 4, 1, 1, 2, 2));
    }

    @Test
    public void testContainsSlightlyBigger() throws Exception {
        assertTrue(Util.contains(2, 0, 2, 4, 2, 0, 2, 3));
    }

    @Test
    public void testNotContains() throws Exception {
        assertFalse(Util.contains(2, 0, 2, 3, 2, 0, 4, 2));
    }
}