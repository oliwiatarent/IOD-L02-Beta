package pl.put.poznan.sortingmadness.rest;

import java.util.List;

/**
 * Klasa reprezentująca dane wejściowe dla sortowania.
 *
 * Jest mapowana z JSONa.
 * Zawiera listę danych do posortowania oraz opcje sortowania.
 */
public class SortingMadnessInput {

    /**
     * Lista elementów do posortowania. Może zawierać liczby, ciągi znaków lub mapy (obiekty JSON).
     */
    private List<?> list;

    /**
     * Kierunek sortowania.
     * <ul>
     * <li>{@code true} - sortowanie rosnące</li>
     * <li>{@code false} - sortowanie malejące</li>
     * </ul>
     */
    private Boolean ascending;

    /**
     * Wybrany algorytm sortowania.
     * <ul>
     * <li>1 - Bubble Sort</li>
     * <li>2 - Merge Sort</li>
     * <li>3 - Selection Sort</li>
     * <li>4 - Insertion Sort</li>
     * <li>5 - Quick Sort</li>
     * <li>6 - Bogo Sort</li>
     * </ul>
     */
    private Integer algorithm;

    /**
     * Maksymalna liczba iteracji algorytmu (dla algorytmów iteracyjnych).
     * Jeśli {@code null} lub {@code <= 0}, limit jest ignorowany.
     */
    private Integer iterations;

    /**
     * Flaga automatycznego doboru algorytmu.
     * <ul>
     * <li>{@code true} - aplikacja sama dobierze optymalny algorytm na podstawie danych</li>
     * <li>{@code false} - używany jest algorytm wskazany w polu {@link #algorithm}</li>
     * </ul>
     */
    private Boolean autoChoose;

    /**
     * Nazwa klucza, po którym ma odbywać się sortowanie.
     * Wymagane, gdy lista zawiera obiekty złożone.
     */
    private String property;


    /**
     * Pobiera listę elementów do posortowania.
     * @return lista obiektów
     */
    public List<?> getList() {
        return list;
    }

    /**
     * Sprawdza preferowany kierunek sortowania.
     * @return {@code true} dla rosnąco, {@code false} dla malejąco
     */
    public Boolean getAscending() {
        return ascending;
    }

    /**
     * Pobiera identyfikator wybranego algorytmu.
     * @return liczba całkowita reprezentująca algorytm (1-6)
     */
    public Integer getAlgorithm() {
        return algorithm;
    }

    /**
     * Pobiera liczbę iteracji.
     * @return liczba iteracji lub {@code null}
     */
    public Integer getIterations() {
        return iterations;
    }

    /**
     * Sprawdza, czy włączony jest tryb automatycznego wyboru algorytmu.
     * @return {@code true} jeśli wybrano tryb automatyczny
     */
    public Boolean getAutoChoose() {
        return autoChoose;
    }

    /**
     * Pobiera nazwę klucza, po którym mają być sortowane obiekty.
     * @return nazwa pola lub {@code null} dla list prostych
     */
    public String getProperty() {
        return property;
    }
}
