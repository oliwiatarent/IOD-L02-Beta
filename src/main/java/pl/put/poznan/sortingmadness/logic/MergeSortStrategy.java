package pl.put.poznan.sortingmadness.logic;

import static pl.put.poznan.sortingmadness.logic.SortingMadness.SortingLogger;

/**
 * Implementacja algorytmu sortowania przez scalanie (Merge Sort).
 * Algorytm NIE obsługuje parametru liczby iteracji.
 */
public class MergeSortStrategy implements SortStrategy {
    public Integer [] sort (String[] Data, Integer[] Indexes, int limit){
        SortingLogger.debug("mergesort start\n");
        int N = Data.length;


        split(Data,0,N-1,Indexes);


        SortingLogger.debug("mergesort end\n");
        return Indexes;
    }

    public static void split(String[] Data, int start, int stop, Integer[] Indexes){

        //System.out.println(start);
        //System.out.println(stop);

        if(stop-start>1){
            split(Data,start,(stop+start)/2,Indexes);
            split(Data,(stop+start)/2+1,stop,Indexes);
        }else if(stop-start==0) return;

        int i=start;
        int j=(stop+start)/2+1;



        int k=0;
        String[] newData = new String[stop-start+1];
        Integer[] newIndexes = new Integer[stop-start+1];
        while(i<=(stop+start)/2 || j<=stop){
            if((j>stop || 0>=Data[i].compareTo(Data[j])) && i<=(start+stop)/2){
                newData[k] = Data[i];
                newIndexes[k] = Indexes[i];
                i++;
            }else{
                newData[k] = Data[j];
                newIndexes[k] = Indexes[j];
                j++;
            }
            k++;
        }
        for(int h=start;h<=stop;h++){
            Data[h]=newData[h-start];
            Indexes[h]=newIndexes[h-start];
        }

    }
}