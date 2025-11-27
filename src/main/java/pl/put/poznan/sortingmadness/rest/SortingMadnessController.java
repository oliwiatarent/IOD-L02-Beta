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
     public SortingMadnessInput post(@RequestBody SortingMadnessInput input) {

         if (input.getIntegerList() != null) logger.debug(input.getIntegerList().toString());
         if (input.getFloatList() != null) logger.debug(input.getFloatList().toString());
         if (input.getStringList() != null) logger.debug(input.getStringList().toString());
         logger.debug(input.getAscending().toString());
         logger.debug(input.getIterations().toString());

         return input;
     }
}


