package pl.put.poznan.sortingmadness.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Główna klasa startowa aplikacji SortingMadness.
 * Odpowiada za uruchomienie Spring Boot.
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sortingmadness.rest"})
public class SortingMadnessApplication {

    /**
     * Główna metoda wejściowa.
     * @param args argumenty przekazywane do aplikacji podczas uruchamiania
     */
    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
    }
}
