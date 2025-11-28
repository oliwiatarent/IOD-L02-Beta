package pl.put.poznan.sortingmadness.rest;

import java.util.List;

public class SortingMadnessOutput {

    private long time;
    private Object[] result;
    private Integer[] sortedIndexes;

    public void setTime(long newTime) {
        this.time = newTime;
    }

    public void setResult(Object[] newResult) {
        this.result = newResult;
    }

    public void setSortedIndexes(Integer[] indexes) {
        this.sortedIndexes = indexes;
    }

    public long getTime() {
        return time;
    }

    public Object[] getResult() {
        return result;
    }

    public Integer[] getSortedIndexes() {
        return sortedIndexes;
    }
}
