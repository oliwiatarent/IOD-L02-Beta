package pl.put.poznan.sortingmadness.rest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.SortingMadness;

import java.util.Arrays;


@RestController
@RequestMapping("/sort")
public class SortingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

     @PostMapping(consumes = "application/json", produces = "application/json")
     public SortingMadnessOutput post(@RequestBody SortingMadnessInput input) {

         SortingMadnessOutput output = new SortingMadnessOutput();
         int type = 0;
         int tabLength = 0;

         logger.debug("Input:");
         
         if (input.getIntegerList() != null) {
             type = 1;
             tabLength = input.getIntegerList().length;
             logger.debug(Arrays.toString(input.getIntegerList()));
         }
         if (input.getFloatList() != null) {
             type = 2;
             tabLength = input.getFloatList().length;
             logger.debug(Arrays.toString(input.getFloatList()));
         }
         if (input.getStringList() != null) {
             type = 3;
             tabLength = input.getStringList().length;
             logger.debug(Arrays.toString(input.getStringList()));
         }

         logger.debug(input.getAscending().toString());
         logger.debug(input.getIterations().toString());

         Integer[] Indexes = new Integer [tabLength];
         for (int i = 0; i < tabLength; i++)
             Indexes[i] = i;

         switch (type){
             case 1:
                 output = SortingMadness.ChooseSort(input.getIntegerList(), Indexes, input.getAlgorithm());
                 break;
             case 2:
                 output = SortingMadness.ChooseSort(input.getFloatList(), Indexes, input.getAlgorithm());
                 break;
             case 3:
                 output = SortingMadness.ChooseSort(input.getStringList(), Indexes, input.getAlgorithm());
                 break;
         }

         logger.debug("Response:");
         logger.debug(String.valueOf(output.getTime()));
         logger.debug(Arrays.toString(output.getResult()));

         return output;
     }
}


