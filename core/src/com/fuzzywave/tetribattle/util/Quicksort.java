package com.fuzzywave.tetribattle.util;

/**
 * http://stackoverflow.com/a/1040503/3177465
 */
public class Quicksort {

    public static void quicksort(int[] main, int[] index) {
        quicksort(main, index, 0, index.length - 1);
    }

    // quicksort a[left] to a[right]
    private static void quicksort(int[] a, int[] index, int left, int right) {
        if (right <= left) {
            return;
        }
        int i = partition(a, index, left, right);
        quicksort(a, index, left, i - 1);
        quicksort(a, index, i + 1, right);
    }

    // partition a[left] to a[right], assumes left < right
    private static int partition(int[] a, int[] index,
                                 int left, int right) {
        int i = left - 1;
        int j = right;
        while (true) {
            while (greater(a[index[++i]], a[index[right]])) {
                ;
            }
            while (greater(a[index[right]], a[index[--j]])) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(index, i, j);
        }
        exch(index, i, right);
        return i;
    }


    private static boolean greater(int x, int y) {
        return (x > y);
    }

    // exchange a[i] and a[j]
    private static void exch(int[] index, int i, int j) {
        int b = index[i];
        index[i] = index[j];
        index[j] = b;
    }
}
