package pl.put.poznan.sortingmadness.rest;

import java.util.List;

public class SortingMadnessInput {

    private Object[] list;
    private Boolean ascending;                  // 1 - rosnaco, 0 - malejaco
    private Integer algorithm;                  // 1 - bubblesort 2 - mergesort 3 - selectionsort 4 - insertsort 5 - quicksort 6 - bogosort)");
    private Integer iterations;
    private Boolean autoChoose;                 // 1 - automatyczne wybieranie algorytmu, 0 - uzytkownik wybiera

    public Object[] getList() {
        return list;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public Integer getAlgorithm() {
        return algorithm;
    }

    public Integer getIterations() {
        return iterations;
    }

    public Boolean getAutoChoose() {
        return autoChoose;
    }
}
