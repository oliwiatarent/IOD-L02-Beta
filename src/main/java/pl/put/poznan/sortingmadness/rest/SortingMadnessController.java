package pl.put.poznan.sortingmadness.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.SortingMadness;

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
         if (input.getAlgorithm() == null) {
             throw new IllegalArgumentException("Musisz wskazać numer algorytmu.");
         }
         int algorithm = input.getAlgorithm();
         if (algorithm < 1 || algorithm > 6) {
             throw new IllegalArgumentException("Nieprawidłowy numer algorytmu.");
         }

         SortingMadnessOutput output = new SortingMadnessOutput();
         List<?> list = input.getList();
         int tabLength = list.size();
         float time = 0;

         Integer[] indexes = new Integer [tabLength];
         for (int i = 0; i < tabLength; i++)
             indexes[i] = i;

         Object[] values = new Object[tabLength];

         boolean isObjectList = list.get(0) instanceof Map;

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

         boolean ascending = input.getAscending();
         output = SortingMadness.ChooseSort(values, indexes, algorithm, ascending);

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


