package pl.put.poznan.sortingmadness.logic;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import static pl.put.poznan.sortingmadness.logic.SortingMadness.SortingLogger;

/**
 * Implementacja algorytmu sortowania przez wstawianie (Insertion Sort).
 * Algorytm obsługuje parametr liczby iteracji.
 */
public class InsertSortStrategy implements SortStrategy {
    public Integer [] sort (String[] Data, Integer[] Indexes, int limit){
        SortingLogger.debug("insert sort start\n");
        int N = Data.length;

        for(int i=0;i<N-1 && i<limit;i++){
            int j=i+1;
            while(j>0 && 0>Data[j].compareTo(Data[j-1])){
                String tmp=Data[j];
                Data[j] = Data[j-1];
                Data[j-1]=tmp;
                int Indextmp=Indexes[j];
                Indexes[j]=Indexes[j-1];
                Indexes[j-1]=Indextmp;
                j--;
            }
        }
        SortingLogger.debug("insert sort end\n");
        return Indexes;
    }
}