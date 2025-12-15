package pl.put.poznan.sortingmadness.logic;

/**
 * Interfejs strategii sortowania.
 */
public interface SortStrategy {
    /**
     * Metoda sortująca dane.
     *
     * @param data    tablica {@code String} z danymi do posortowania
     * @param indexes tablica oryginalnych indeksów
     * @param limit   maksymalna liczba iteracji algorytmu
     * @return posortowana tablica indeksów
     */
    Integer[] sort(String[] data, Integer[] indexes, int limit);
}