package pl.put.poznan.sortingmadness.logic;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import static pl.put.poznan.sortingmadness.logic.SortingMadness.SortingLogger;

/**
 * Implementacja algorytmu sortowania przez wybór (Selection Sort).
 * Algorytm obsługuje parametr liczby iteracji.
 * Przy liczbie iteracji mniejszej niż liczba elementów wypełnia puste elementy ostatnim posortowanym.
 */
public class SelectionSortStrategy implements SortStrategy {
    public Integer [] sort (String[] Data, Integer[] Indexes, int limit){
        SortingLogger.debug("selection sort start\n");
        int N = Data.length;
        Integer[] newIndexes = new Integer[N];
        for(int i=0;i<N && i<limit;i++){
            String min="";
            Integer m=0;
            for(int j=0;j<N;j++){
                if((min=="" || 0<min.compareTo(Data[j])) && Data[j] != ""){
                    min=Data[j];
                    m=Indexes[j];
                }
            }
            newIndexes[i]=m;
            Data[m]="";
        }

        // kopiowanie posortowanej wartości do pustych elementów tablicy wyjściowej
        if (limit < N) {
            for (int i=0; i<N; i++) {
                if (newIndexes[i] == null) newIndexes[i] = newIndexes[limit-1];
            }
        }

        Indexes=newIndexes;
        SortingLogger.debug("selection sort end\n");
        return Indexes;
    }
}