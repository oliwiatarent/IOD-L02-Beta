package pl.put.poznan.sortingmadness.rest;

import java.util.List;

/**
 * Klasa reprezentująca wynik operacji sortowania.
 *
 * Zawiera posortowane dane, czas wykonania operacji oraz indeksy elementów.
 */
public class SortingMadnessOutput {

    /**
     * Czas wykonania sortowania [ms].
     */
    private long time;

    /**
     * Tablica zawierająca posortowane elementy.
     */
    private Object[] result;

    /**
     * Tablica indeksów z oryginalnej listy po posortowaniu.
     */
    private Integer[] sortedIndexes;


    /**
     * Ustawia czas wykonania sortowania.
     * @param newTime czas [ms]
     */
    public void setTime(long newTime) {
        this.time = newTime;
    }

    /**
     * Ustawia wynikową tablicę posortowanych obiektów.
     * @param newResult tablica posortowanych obiektów
     */
    public void setResult(Object[] newResult) {
        this.result = newResult;
    }

    /**
     * Ustawia wynikową tablicę posortowanych indeksów.
     * @param indexes tablica indeksów
     */
    public void setSortedIndexes(Integer[] indexes) {
        this.sortedIndexes = indexes;
    }

    /**
     * Pobiera czas wykonania sortowania.
     * @return czas w milisekundach
     */
    public long getTime() {
        return time;
    }

    /**
     * Pobiera posortowane obiekty.
     * @return tablica posortowanych obiektów
     */
    public Object[] getResult() {
        return result;
    }

    /**
     * Pobiera posortowane indeksy.
     * @return tablica posortowanych indeksów {@code int}
     */
    public Integer[] getSortedIndexes() {
        return sortedIndexes;
    }
}
