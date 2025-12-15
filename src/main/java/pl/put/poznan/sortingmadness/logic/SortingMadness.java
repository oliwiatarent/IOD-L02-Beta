package pl.put.poznan.sortingmadness.logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import pl.put.poznan.sortingmadness.rest.SortingMadnessOutput;

/**
 * Klasa zarządzająca logiką sortowania.
 */
public class SortingMadness {

    /**
     * Aktualnie wybrana strategia sortowania.
     */
    private SortStrategy strategy;

    /**
     * Logger dla klasy.
     */
    public static Logger SortingLogger = LoggerFactory.getLogger(SortingMadness.class);

    /**
     * Konstruktor.
     */
    public SortingMadness () {
        SortingLogger.info("Hello World\n");
    }

    /**
     * Ustawia algorytm sortowania.
     * @param strategy obiekt implementujący interfejs SortStrategy
     */
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Konwertuje tablicę obiektów na tablicę {@code String} do porównywania.
     *
     * Metoda obsługuje liczby zmiennoprzecinkowe, dodając wiodące i kończące zera,
     * aby porównywanie leksykograficzne dawało poprawne wyniki.
     *
     * @param Data tablica obiektów do konwersji
     * @return tablica {@code String}
     */
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

    /**
     * Przeprowadza sortowanie danych odpowiednim algorytmem.
     *
     * @param Data tablica obiektów do posortowania
     * @param Indexes tablica początkowych indeksów
     * @param ascending kierunek sortowania ({@code true} - rosnąco, {@code false} - malejąco)
     * @param iterations liczba iteracji
     * @return obiekt {@link SortingMadnessOutput} zawierający wynik, czas i posortowane indeksy
     */
    public SortingMadnessOutput sort(Object[] Data, Integer[] Indexes, Boolean ascending, Integer iterations) {
        SortingLogger.info("Starting sort. Options: ascending=" + ascending + ", iterations=" + iterations);

        SortingMadnessOutput output = new SortingMadnessOutput();

        int len = Indexes.length;
        int limit = (iterations != null && iterations > 0) ?  iterations : Integer.MAX_VALUE;
        String[] s = convert(Data);
        Integer[] wynik;
        Object[] wynikLista = new Object[len];

        long startTime = System.currentTimeMillis();

        wynik = strategy.sort(s, Indexes, limit);

        if (!ascending) {
            Integer[] reversed = new Integer[len];
            for (int i = 0; i < len; i++) {
                reversed[i] = wynik[len - 1 - i];
            }
            wynik = reversed;
        }

        for (int i = 0; i < len; i++) {
            wynikLista[i] = Data[wynik[i]];
            String logeroutput = wynikLista[i].toString();
            SortingLogger.info(logeroutput);
        }

        long czas = System.currentTimeMillis() - startTime;

        output.setTime(czas);
        output.setResult(wynikLista);
        output.setSortedIndexes(wynik);

        return output;
    }

    @Deprecated(forRemoval = true)
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
