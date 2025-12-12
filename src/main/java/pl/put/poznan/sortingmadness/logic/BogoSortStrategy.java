package pl.put.poznan.sortingmadness.logic;

import java.util.concurrent.ThreadLocalRandom;

public class BogoSortStrategy implements SortStrategy{
    public Integer [] sort(String[] Data, Integer[] Indexes, int limit){
        int N = Data.length;
        boolean[] used = new boolean [N];
        for(int i=0;i<N;i++)used[i]=false;

        // do zliczania iteracji
        int counter=0;
        boolean stop=false;
        while (!stop && counter < limit) {
            counter++;
            stop=true;
            for(int i=1;i<N;i++){
                if(0<Data[Indexes[i-1]].compareTo(Data[Indexes[i]])){
                    stop=false;
                    break;
                }
            }
            if(!stop){
                int maxi=Data.length;
                for(int i=0;i<N;i++){
                    int a= ThreadLocalRandom.current().nextInt(0, maxi);
                    while(used[a]) a=ThreadLocalRandom.current().nextInt(0, maxi);
                    Indexes[i]=a;
                    used[a]=true;
                }
                for(int i=0;i<N;i++)used[i]=false;
            }
        }



        return Indexes;
    }
}
