package pl.put.poznan.sortingmadness.logic;

public interface SortStrategy {
    Integer[] sort(String[] data, Integer[] indexes, int limit);
}