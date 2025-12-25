package pl.put.poznan.sortingmadness.logic;

import java.util.ArrayList;

import static pl.put.poznan.sortingmadness.logic.SortingMadness.SortingLogger;

/**
 * Implementacja algorytmu sortowania szybkiego (Quick Sort).
 * Algorytm NIE obsługuje parametru liczby iteracji.
 */
public class QuickSortStrategy implements SortStrategy{
    /**
     * Sortuje dane quicksortem.
     *
     * @param Data    tablica danych do posortowania
     * @param Indexes tablica indeksów
     * @param limit   brak implementacji
     * @return tablica indeksów po posortowaniu
     */
    public Integer [] sort (String[] Data, Integer[] Indexes, int limit){
        SortingLogger.debug("quicksort start\n");
        int N = Data.length;

        qs(Data,Indexes);

        SortingLogger.debug("quicksort end\n");
        return Indexes;
    }

    /**
     * Rekurencyjna metoda do implementacji podziału i pivota.
     *
     * @param Data    tablica z danymi
     * @param Indexes tablica indeksów
     */
    public static void qs(String[] Data, Integer[] Indexes){
        if(Data.length<=1)return;
        String pivot = Data[0];
        int pivotIndex = Indexes[0];
        ArrayList<String> lesser = new ArrayList<String>();
        ArrayList <Integer>  lesserIndex= new ArrayList<Integer>();
        ArrayList <String>  greater= new ArrayList<String>();
        ArrayList <Integer>  greaterIndex= new ArrayList<Integer>();
        //System.out.println(Data.length);
        for(int i=1;i<Data.length;i++){
            if(0<pivot.compareTo(Data[i])){
                //System.out.println("lesser "+Data[i]+" "+pivot);
                lesser.add(Data[i]);
                lesserIndex.add(Indexes[i]);
            }else{
                //System.out.println("greater "+Data[i]+" "+pivot);
                greater.add(Data[i]);
                greaterIndex.add(Indexes[i]);
            }
        }

        Integer[] I2 = new Integer[greater.size()];
        String[] g = new String[greater.size()];
        String[] l = new String[lesser.size()];
        Integer[] I = new Integer[lesser.size()];

        for(int i=0;i<lesser.size();i++){

            l[i] = lesser.get(i);
            I[i] = lesserIndex.get(i);
        }
        for(int i=0;i<greater.size();i++){
            //System.out.println((String)greater.get(i) + " " + pivot);
            g[i] = greater.get(i);
            I2[i] = greaterIndex.get(i);
        }

        qs(l,I);
        qs(g,I2);

        for(int i=0;i<lesser.size();i++){
            Data[i] = l[i];
            Indexes[i] = I[i];
        }
        Data[lesser.size()]=pivot;
        Indexes[lesser.size()]=pivotIndex;
        for(int i=0;i<greater.size();i++){
            Data[i+lesser.size()+1] = g[i];
            Indexes[i+lesser.size()+1] = I2[i];
        }

    }
}