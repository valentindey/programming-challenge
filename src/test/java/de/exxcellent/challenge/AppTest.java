package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    final private String resourcePath = "src/main/resources/de/exxcellent/challenge/";

    @Test
    void runFootball() {
        App.main("--football", resourcePath + "football.csv");
    }


    @Test
    void testGetMinDiffNameFootball() {
        String teamWithSmallestGoalSpread = App.getMinDiffName(
                resourcePath + "football.csv", "Team", "Goals", "Goals Allowed"
        );
        assertEquals("Aston_Villa", teamWithSmallestGoalSpread,
                "team with smallest goal spread should be found");
    }

    @Test
    void runWeather() {
        App.main("--weather", resourcePath + "weather.csv");
    }

    @Test
    void testGetMinDiffNameWeather() {
        String dayWithSmallestTempSpread = App.getMinDiffName(
                resourcePath + "weather.txt", "Day", "MxT", "MnT"
        );
        assertEquals("14", dayWithSmallestTempSpread,
                "day with smallest temperature spread should be found");
    }

}