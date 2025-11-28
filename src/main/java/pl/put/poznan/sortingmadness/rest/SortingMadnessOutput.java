package pl.put.poznan.sortingmadness.rest;

import java.util.List;

public class SortingMadnessOutput {

    private Float time;
    private Object[] result;

    public void setTime(Float newTime) {
        this.time = newTime;
    }

    public void setResult(Object[] newResult) {
        this.result = newResult;
    }

    public Float getTime() {
        return time;
    }

    public Object[] getResult() {
        return result;
    }
}
