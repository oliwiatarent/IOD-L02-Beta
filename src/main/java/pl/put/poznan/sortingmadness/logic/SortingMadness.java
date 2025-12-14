package pl.put.poznan.sortingmadness.logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import pl.put.poznan.sortingmadness.rest.SortingMadnessOutput;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SortingMadness {

    public static Logger SortingLogger = LoggerFactory.getLogger(SortingMadness.class);
    

    public SortingMadness () {
        SortingLogger.info("Hello World\n");
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

    public static Integer [] bubblesort (String[] Data, Integer[] Indexes, int limit){
        SortingLogger.debug("bubblesort start\n");
        Integer N = Data.length;

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
    public static Integer [] mergesort (String[] Data, Integer[] Indexes){
        SortingLogger.debug("mergesort start\n");
        int N = Data.length;


        split(Data,0,N-1,Indexes);

        SortingLogger.debug("mergesort end\n");
        return Indexes;
    }

    public static Integer [] selectionsort (String[] Data, Integer[] Indexes, int limit){
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


    public static Integer [] insertsort (String[] Data, Integer[] Indexes, int limit){
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
    public static Integer [] quicksort (String[] Data, Integer[] Indexes){
        SortingLogger.debug("quicksort start\n");
        int N = Data.length;

        qs(Data,Indexes);
        SortingLogger.debug("quicksort end\n");
        return Indexes;
    }

    public static Integer [] bogosort(String[] Data, Integer[] Indexes, int limit){
        SortingLogger.debug("bogosort start\n");
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


        SortingLogger.debug("bogosort end\n");
        return Indexes;
    }
    public static String [] convert(Object[] Data){
        SortingLogger.info("started converting");
        int N = Data.length;

        String[] s = new String [N];
        int max=0;
        int dec=0;
        int dec_max=0;
        for(int i=0;i<N;i++){
            s[i]=Data[i].toString();
            dec=s[i].length()-s[i].indexOf(".");
            if(dec>dec_max)dec_max=dec;
        }
        if(Data[0].getClass()!=s[0].getClass())for(int i=0;i<N;i++) if(s[i].contains(".")) while(s[i].length()-s[i].indexOf(".")<dec_max) s[i]=s[i]+"0";

        for(int i=0;i<N;i++) if(s[i].length()>max)max=s[i].length();
        if(Data[0].getClass()!=s[0].getClass())for(int i=0;i<N;i++) while(s[i].length()<max)s[i]="0"+s[i];

        return s;
    }

    public static SortingMadnessOutput ChooseSort(Object[] Data, Integer[] Indexes, int choice, Boolean ascending, Integer iterations) {
        SortingLogger.info("Choosing sort");

        SortingMadnessOutput output = new SortingMadnessOutput();

        int len = Indexes.length;
        int limit = (iterations != null && iterations > 0) ?  iterations : Integer.MAX_VALUE;
        String[] s = convert(Data); // TODO: jeśli chcemy żeby wyjście JSON zachowywało typ z wejścia to by trzeba przerobić
        Integer[] wynik = new Integer[len];
        Object[] wynikLista = new Object[len];

        long startTime = System.currentTimeMillis();

        if(choice==1)wynik=bubblesort(s,Indexes, limit);
        if(choice==2)wynik=mergesort(s,Indexes);            //bez liczby iteracji
        if(choice==3)wynik=selectionsort(s,Indexes, limit);
        if(choice==4)wynik=insertsort(s,Indexes, limit);
        if(choice==5)wynik=quicksort(s,Indexes);            //bez liczby iteracji
        if(choice==6)wynik=bogosort(s,Indexes, limit);

//        long czas = System.currentTimeMillis() - startTime;

        if (!ascending) {
            Integer[] reversed = new Integer[len];
            for (int i = 0; i < len; i++) {
                reversed[i] = wynik[len - 1 - i];
            }
            wynik = reversed;
        }

        for (int i = 0; i < len; i++) {
            wynikLista[i] = Data[wynik[i]];
        }

        long czas = System.currentTimeMillis() - startTime;

        output.setTime(czas);
        output.setResult(wynikLista);
        output.setSortedIndexes(wynik);

        return output;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Integer[] Data = new Integer [10000];
        for(int i=0;i<10000;i++)Data[i]=ThreadLocalRandom.current().nextInt(0, 10000);
        String[] Data2 = {"42","1000.5","abc"};
        Double[] Data3 = {3.55,12.0,99.0,8.0};
        Integer[] Indexes = new Integer [Data.length];

        for(int i=0;i<Data.length;i++) Indexes[i]=i;

        Scanner read = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter algorithm to sort data (1-bubblesort 2-mergesort 3-selectionsort 4-insertsort 5-quicksort 6-bogosort)");

        int choice = Integer.parseInt(read.nextLine());  // Read user input

        // float time = ChooseSort(Data,Indexes,choice);
        // System.out.println(time);
    }
}
