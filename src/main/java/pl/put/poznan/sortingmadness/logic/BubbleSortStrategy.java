package pl.put.poznan.sortingmadness.logic;

import static pl.put.poznan.sortingmadness.logic.SortingMadness.SortingLogger;

/**
 * Implementacja algorytmu sortowania bąbelkowego (Bubble Sort).
 * Algorytm obsługuje parametr liczby iteracji.
 */
public class BubbleSortStrategy implements SortStrategy {
    /**
     * Sortuje dane bąbelkowo.
     *
     * @param Data    tablica danych do posortowania
     * @param Indexes tablica indeksów
     * @param limit   maksymalna liczba iteracji pętli
     * @return tablica indeksów po posortowaniu
     */
    @Override
    public Integer [] sort (String[] Data, Integer[] Indexes, int limit){
        SortingLogger.debug("bubblesort start\n");
        int N = Data.length;

        for(int i=0;i<N && i<limit; i++){
            for(int j=0;j<N;j++){
                if(0>Data[i].compareTo(Data[j])){
                    String tmp=Data[i];
                    Data[i] = Data[j];
                    Data[j]=tmp;
                    int Indextmp=Indexes[i];
                    Indexes[i]=Indexes[j];
                    Indexes[j]=Indextmp;
                }
            }
        }
        SortingLogger.debug("bubblesort end\n");
        return Indexes;
    }
}