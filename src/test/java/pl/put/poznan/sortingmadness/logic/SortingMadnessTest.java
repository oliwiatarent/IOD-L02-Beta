package pl.put.poznan.sortingmadness.logic;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.put.poznan.sortingmadness.rest.SortingMadnessOutput;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SortingMadnessTest {

    SortingMadness sortingMadness;
    SortStrategy mockSortStrategy;

    @BeforeEach
    public void setup() {
        sortingMadness = new SortingMadness();
        mockSortStrategy = mock(SortStrategy.class);
        Mockito.reset(mockSortStrategy);
    }

    @Test
    public void testConvertIntegers() {
        Integer[] Data = {42, 7, 89, 13, 56, 2, 91, 34, 68, 25};
        String[] result = {"42", "07", "89", "13", "56", "02", "91", "34", "68", "25"};

        assertArrayEquals(sortingMadness.convert(Data), result);
    }

    @Test
    public void testConvertFloats() {
        Double[] Data = {42.7, 7.13, 89.56, 13.02, 56.84, 2.91, 91.34, 34.68, 68.25, 25.49};
        String[] result = {"42.70", "07.13", "89.56", "13.02", "56.84", "02.91", "91.34", "34.68", "68.25", "25.49"};

        assertArrayEquals(sortingMadness.convert(Data), result);
    }

    @Test
    public void testSortStrategy1 () {

        String[] Data = {"42", "7", "89", "13", "56", "2", "91", "34", "68", "25", "77", "4", "59", "18", "83", "30", "11", "96", "50", "66"};
        Integer[] Indexes = new Integer [Data.length];
        Boolean ascending = true;
        Integer iterations = 0;
        Integer[] sortedIndexes = {5 ,11, 1, 16, 3, 13, 9, 15, 7, 0, 18, 4, 12, 19, 8, 10, 14, 2, 6, 17};

        when(mockSortStrategy.sort(
                any(String[].class),
                any(Integer[].class),
                eq(Integer.MAX_VALUE)
        )).thenReturn(sortedIndexes);

        sortingMadness.setStrategy(mockSortStrategy);
        SortingMadnessOutput output = sortingMadness.sort(Data, Indexes, ascending, iterations);

        assertArrayEquals(sortedIndexes, output.getSortedIndexes());
    }

    @Test
    public void testSortStrategy2 () {

        String[] Data = {"42", "7", "89", "13", "56", "2", "91", "34", "68", "25", "77", "4", "59", "18", "83", "30", "11", "96", "50", "66"};
        Integer[] Indexes = new Integer [Data.length];
        Boolean ascending = false;
        Integer iterations = 0;
        Integer[] sortedIndexes = {5 ,11, 1, 16, 3, 13, 9, 15, 7, 0, 18, 4, 12, 19, 8, 10, 14, 2, 6, 17};
        Integer[] finalResult = {17, 6, 2, 14, 10, 8, 19, 12, 4, 18, 0, 7, 15, 9, 13, 3, 16, 1, 11, 5};

        when(mockSortStrategy.sort(
                any(String[].class),
                any(Integer[].class),
                eq(Integer.MAX_VALUE)
        )).thenReturn(sortedIndexes);

        sortingMadness.setStrategy(mockSortStrategy);
        SortingMadnessOutput output = sortingMadness.sort(Data, Indexes, ascending, iterations);

        assertArrayEquals(finalResult, output.getSortedIndexes());
    }

    @Test
    public void testSortStrategy3 () {

        String[] Data = {"42", "7", "89", "13", "56", "2", "91", "34", "68", "25", "77", "4", "59", "18", "83", "30", "11", "96", "50", "66", "38", "14", "72", "5", "61", "27", "80", "9", "44", "21"};
        Integer[] Indexes = new Integer [Data.length];
        Boolean ascending = true;
        Integer iterations = 2;
        Integer[] sortedIndexes = {5, 11, 1, 13, 3, 15, 9, 0, 6, 7, 8, 10, 2, 19, 14, 4, 16, 17, 18, 12, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};

        when(mockSortStrategy.sort(
                any(String[].class),
                any(Integer[].class),
                eq(2)
        )).thenReturn(sortedIndexes);

        sortingMadness.setStrategy(mockSortStrategy);
        SortingMadnessOutput output = sortingMadness.sort(Data, Indexes, ascending, iterations);

        assertArrayEquals(sortedIndexes, output.getSortedIndexes());
    }
}