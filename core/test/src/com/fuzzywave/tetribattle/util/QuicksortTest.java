package com.fuzzywave.tetribattle.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuicksortTest {

    int[] mainArray = {6, 43, 9, 7, 74, 1, 3, 73};
    int[] indexArray = {0, 1, 2, 3, 4, 5, 6, 7};

    int[] resultArray = {6, 43, 9, 7, 74, 1, 3, 73};
    int[] resultIndexArray = {4, 7, 1, 2, 3, 0, 6, 5};

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testQuicksort() throws Exception {
        Quicksort.quicksort(mainArray, indexArray);
        assertArrayEquals(resultArray, mainArray);
        assertArrayEquals(resultIndexArray, indexArray);
    }
}