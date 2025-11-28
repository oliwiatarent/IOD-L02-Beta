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
         int tabLength = input.getList().length;
         float time = 0;

         logger.debug("Wejście:");
         logger.debug(Arrays.toString(input.getList()));
         logger.debug(input.getAlgorithm().toString());
         logger.debug(input.getIterations().toString());

         Integer[] Indexes = new Integer [tabLength];
         for (int i = 0; i < tabLength; i++)
             Indexes[i] = i;

         output = SortingMadness.ChooseSort(input.getList(), Indexes, input.getAlgorithm());

         logger.debug("Odpowiedź:");
         logger.debug(String.valueOf(output.getTime()));
         logger.debug(Arrays.toString(output.getResult()));

         return output;
     }
}


