package pl.put.poznan.sortingmadness.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sort")
public class SortingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

     @PostMapping(consumes = "application/json", produces = "application/json")
     public SortingMadnessOutput post(@RequestBody SortingMadnessInput input) {

         if (input.getList().isEmpty()) {
             throw new IllegalArgumentException("Lista nie może być pusta.");
         }

         SortingMadnessOutput output;
         List<?> list = input.getList();
         int tabLength = list.size();
         float time = 0;

         Integer[] indexes = new Integer [tabLength];
         for (int i = 0; i < tabLength; i++)
             indexes[i] = i;

         Object[] values = new Object[tabLength];

         boolean isObjectList = list.get(0) instanceof Map;

         // jeśli lista obiektów to pobieramy własności, po których będziemy sortować
         if (isObjectList) {
             if (input.getProperty() == null || input.getProperty().isEmpty()) {
                 throw new IllegalArgumentException("Nie podano własności, po której sortować listę obiektów.");
             }
             String property = input.getProperty();

             for (int i = 0; i < tabLength; i++){
                 Map<String, Object> obj = (Map<String, Object>) list.get(i);
                 if (obj == null) {
                     throw new IllegalArgumentException("Obiekt" + i + " na liście jest null.");
                 }
                 Object val = obj.get(property);
                 if (val == null) {
                     throw new IllegalArgumentException("Obiekt " + i + " nie ma pola " + property + ".");
                 }
                 values[i] = val;
             }
         // lista jednowymiarowa
         } else {
             if (input.getProperty() != null) {
                 throw new IllegalArgumentException("Podano sortowanie po polu dla listy jednowymiarowej.");
             }
             for (int i = 0; i < tabLength; i++) {
                 Object val = list.get(i);
                 if (val == null) {
                     throw new IllegalArgumentException("Element" + i + " na liście jest null.");
                 }
                 values[i] = val;
             }
         }

         Integer algorithm = input.getAlgorithm();
         // czy użytkownik chce automatycznego doboru algorytmu
         // TODO: tutaj może logowanie wybranego algorytmu
         if (Boolean.TRUE.equals(input.getAutoChoose())) {
             // czy pierwsze 10 elementów jest posortowanych
             boolean appearsSorted = true;
             int checkLimit = Math.min(tabLength - 1, 10);
             for (int i = 0; i < checkLimit; i++) {
                 if (values[i].toString().compareTo(values[i + 1].toString()) > 0)
                     appearsSorted = false;
             }

             // InsertionSort O(n^2)
             // dla posortowanych zbiorów danych - szybszy, nawet do O(n)
             if (appearsSorted) algorithm = 4;
             // BogoSort O(n*n!)
             // dla bardzo małych zbiorów danych
             else if (tabLength <= 5) algorithm = 6;
             // BubbleSort O(n^2)
             // dla małych zbiorów danych
             else if (tabLength <= 30) algorithm = 1;
             // SelectionSort O(n^2)
             // dla średnich zbiorów danych - mniej zapisów do pamięci
             else if (tabLength <= 100) algorithm = 3;
             // MergeSort O(n log n)
             // dla dużych zbiorów danych
             else if (tabLength <= 5000) algorithm = 2;
             // QuickSort O(n log n)
             // dla ogromnych zbiorów danych
             else algorithm = 5;
         }
         else if (algorithm == null) {
             throw new IllegalArgumentException("Musisz wskazać numer algorytmu.");
         }

         if (algorithm < 1 || algorithm > 6) {
             throw new IllegalArgumentException("Nieprawidłowy numer algorytmu.");
         }

         // ustawienie typu i porządku sortowania
         boolean ascending = input.getAscending();
         SortingMadness sm = new SortingMadness();
         switch (algorithm) {
             case 1: sm.setStrategy(new BubbleSortStrategy());    break;
             case 2: sm.setStrategy(new MergeSortStrategy());     break;
             case 3: sm.setStrategy(new SelectionSortStrategy()); break;
             case 4: sm.setStrategy(new InsertSortStrategy());    break;
             case 5: sm.setStrategy(new QuickSortStrategy());     break;
             case 6: sm.setStrategy(new BogoSortStrategy());      break;
         }
         output = sm.sort(values, indexes, ascending, input.getIterations());

        if (isObjectList) {
            Object[] resultObjects = new Object[list.size()];
            Integer[] sortedIndexes = output.getSortedIndexes();
            for (int i = 0; i < list.size(); i++) {
                resultObjects[i] = list.get(sortedIndexes[i]);
            }
            output.setResult(resultObjects);
        }

         return output;
     }
}


