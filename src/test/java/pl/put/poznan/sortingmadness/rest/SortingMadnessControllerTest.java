package pl.put.poznan.sortingmadness.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import pl.put.poznan.sortingmadness.logic.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

public class SortingMadnessControllerTest {

    SortingMadnessController controller;
    SortingMadness mockSortingMadness;
    SortStrategy mockSortStrategy;

    @BeforeEach
    public void setup() {
        mockSortingMadness = mock(SortingMadness.class);
        mockSortStrategy = mock(SortStrategy.class);
        controller = new SortingMadnessController(mockSortingMadness);
    }

    @Test
    public void testEmptyListThrowsError() {
        SortingMadnessInput input = new SortingMadnessInput(
                List.of(),
                false,
                true,
                1,
                10000,
                null
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> controller.post(input));
        assertEquals("Lista nie może być pusta.", ex.getMessage());
    }

    @Test
    public void testNullElementThrowsError() {
        List<Integer> data = Arrays.asList(5, 16, 18, 24, 27, null, 8, 12);
        SortingMadnessInput input = new SortingMadnessInput(
                data,
                false,
                true,
                1,
                10000,
                null
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> controller.post(input));
        assertEquals("Element " + 5 + " na liście jest null.", ex.getMessage());
    }

    @Test
    public void testMissingPropertyThrowsError() {
        List<Map<String, Integer>> data = List.of(Map.of("score", 22));
        SortingMadnessInput input = new SortingMadnessInput(
                data,
                false,
                true,
                5,
                1000,
                null
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> controller.post(input));
        assertEquals("Nie podano własności, po której sortować listę obiektów.", ex.getMessage());
    }

    @Test
    public void testAutoChooseInsertSort() {
        List<Integer> data = List.of(1, 2, 2, 3, 3, 4, 5, 5, 5, 6);
        SortingMadnessInput input = new SortingMadnessInput(
                data,
                true,
                true,
                null,
                10000,
                null
        );

        SortingMadnessOutput mockOutput = mock(SortingMadnessOutput.class);
        when(mockSortingMadness.sort(any(), any(), anyBoolean(), anyInt())).thenReturn(mockOutput);

        controller.post(input);

        verify(mockSortingMadness).setStrategy(isA(InsertSortStrategy.class));
    }

    @Test
    public void testHandlingObjectsByProperty() {
        List<Map<String, Object>> data = List.of(
                Map.of("score", 50),
                Map.of("score", 10),
                Map.of("score", 30),
                Map.of("score", 20),
                Map.of("score", 40)
        );
        SortingMadnessInput input = new SortingMadnessInput(
                data,
                false,
                true,
                5,
                1000,
                "score"
        );

        SortingMadnessOutput mockOutput = mock(SortingMadnessOutput.class);
        when(mockOutput.getSortedIndexes()).thenReturn(new Integer[] {1, 3, 2, 4, 0});
        when(mockSortingMadness.sort(any(), any(), anyBoolean(), anyInt())).thenReturn(mockOutput);

        controller.post(input);

        verify(mockSortingMadness).sort(
                eq(new Object[] {50, 10, 30, 20, 40}),
                eq(new Integer[] {0, 1, 2, 3, 4}),
                eq(true),
                eq(1000)
        );
        verify(mockOutput).getSortedIndexes();
        verify(mockOutput).setResult(new Object[] {data.get(1), data.get(3), data.get(2), data.get(4), data.get(0)});
    }
}
