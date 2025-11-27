package pl.put.poznan.sortingmadness.rest;

import java.util.List;

public class SortingMadnessInput {
    private List<Integer> integerList;
    private List<Float> floatList;
    private List<String> stringList;
    private Boolean ascending;
    private Integer algorithm;
    private Integer iterations;

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public List<Float> getFloatList() {
        return floatList;
    }

    public List<String> getStringList() {
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
}
