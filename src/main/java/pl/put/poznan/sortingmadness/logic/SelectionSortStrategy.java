package pl.put.poznan.sortingmadness.logic;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

public class SelectionSortStrategy implements SortStrategy {
    public Integer [] sort (String[] Data, Integer[] Indexes, int limit){
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
        return Indexes;
    }
}
