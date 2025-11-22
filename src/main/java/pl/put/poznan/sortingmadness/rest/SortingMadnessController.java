package pl.put.poznan.sortingmadness.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.SortingMadness;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class SortingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    // @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    // public String get() {
    //     return;
    // }

    // @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    // public String post() {
    //     return;
    // }
}


