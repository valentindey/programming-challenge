package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataServiceTest {

    final private DataService dataService = new DataService();

    @Test
    void testListOfStringsToDouble() {
        List<String> inputList = List.of("1", "2", "3.4");
        List<Double> expectedOutput = List.of(1d, 2d, 3.4d);
        assertEquals(expectedOutput, dataService.listOfStringsToDouble(inputList),
                "should parse a list of valid number strings to list of doubles");
    }

    @Test
    void testListOfStringsToDoubleEmptyInput() {
        assertEquals(Collections.EMPTY_LIST, dataService.listOfStringsToDouble(Collections.emptyList()),
                "should return empty list for empty input");
    }

    @Test
    void testListOfStringsToDoubleInvalidInput() {
        List<String> inputList = List.of("1", "not a number", "3.4");
        assertThrows(NumberFormatException.class, () -> dataService.listOfStringsToDouble(inputList),
                "should throw a NumberFormatException on invalid input");
    }

    @Test
    void testAbsDiffList() {
        List<Double> input1 = List.of(1d, 2d, 3d);
        List<Double> input2 = List.of(1d, 1d, 1d);
        List<Double> expectedOutput = List.of(0d, 1d, 2d);
        assertEquals(expectedOutput, dataService.absDiffList(input1, input2),
                "should calculate diff of two lists");
    }

    @Test
    void testAbsDiffListNegative() {
        List<Double> input1 = List.of(1d, -2d, 3d);
        List<Double> input2 = List.of(-1d, 1d, -1d);
        List<Double> expectedOutput = List.of(2d, 3d, 4d);
        assertEquals(expectedOutput, dataService.absDiffList(input1, input2),
                "should calculate diff of two lists");
    }

    @Test
    void testAbsDiffListSizeMismatch() {
        List<Double> input1 = List.of(1d, 2d, 3d, 4d);
        List<Double> input2 = List.of(-1d, 1d, -1d);
        assertThrows(IllegalArgumentException.class, () -> dataService.absDiffList(input1, input2),
                "should throw an exception when sizes of input don't match");
    }

    @Test
    void testAbsDiffListEmpty() {
        assertEquals(Collections.EMPTY_LIST, dataService.absDiffList(Collections.emptyList(), Collections.emptyList()),
                "should return empty list for empty input");
    }

    @Test
    void testArgMin() {
        List<Double> input = List.of(1d, 2d, 3d);
        assertEquals(0, dataService.argMin(input), "should get index of smallest element");
    }

    @Test
    void testArgMinEmpty() {
        assertEquals(-1, dataService.argMin(Collections.emptyList()),
                "should return -1 on empty input");
    }

}
