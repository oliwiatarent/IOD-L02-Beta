package pl.put.poznan.sortingmadness.rest;

import java.util.List;

public class SortingMadnessInput {

    private Integer[] integerList;
    private Float[] floatList;
    private String[] stringList;     // 1 - int, 2 - float, 3 - string
    private Boolean ascending;                  // 1 - rosnaco, 0 - malejaco
    private Integer algorithm;                  // 1 - bubblesort 2 - mergesort 3 - selectionsort 4 - insertsort 5 - quicksort 6 - bogosort)");
    private Integer iterations;
    private Boolean autoChoose;                 // 1 - automatyczne wybieranie algorytmu, 0 - uzytkownik wybiera

    public Integer[] getIntegerList() {
        return integerList;
    }

    public Float[] getFloatList() {
        return floatList;
    }

    public String[] getStringList() {
        return stringList;
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
