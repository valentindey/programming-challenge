package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test case for CSVReader class
 */
class CSVReaderTest {

    // relative path when maven is run in parent dir
    final private String resourcePath = "src/test/resources/";
    final private CSVReader csvReader = new CSVReader();

    @Test
    void readEmptyFile() {
        List<List<String>> csvContent = assertDoesNotThrow(() -> csvReader.read(resourcePath + "empty.csv"));
        assertEquals(Collections.EMPTY_LIST, csvContent,
                "should return an empty list when reading an empty file");
    }

    @Test
    void readCsvFile() {
        List<List<String>> csvContent = assertDoesNotThrow(
                () -> csvReader.read(resourcePath + "test_weather.csv")
        );
        assertNotNull(csvContent, "read content should not be null");
        assertEquals(5, csvContent.size(), "should read all lines of the small test csv file");
        List<String> expectedFirstLine = List.of(
                "1", "88", "59", "74", "53.8", "0", "280", "9.6" , "270", "17", "1.6", "93", "23", "1004.5"
        );
        assertEquals(expectedFirstLine, csvContent.get(1));
    }

    @Test
    void throwFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> csvReader.read(resourcePath + "does_not_exist.csv"),
                "should throw FileNotFoundException when the requested file does not exist");
    }

    @Test
    void throwHeaderMismatch() {
        assertThrows(InputMismatchException.class, () -> csvReader.read(resourcePath + "header_mismatch.csv"),
                "should throw an InputMismatchException when header has less columns than content");
    }

    @Test
    void throwInvalidContent() {
        assertThrows(InputMismatchException.class, () -> csvReader.read(resourcePath + "bad_content.csv"),
                "should throw an InputMismatchException when a file is read with non-csv content");
    }

    @Test
    void extractColumn() {
        List<List<String>> csvData = List.of(
                List.of("header", "row", "requestedCol"),
                List.of("data0", "data1", "get"),
                List.of("data2", "data4", "this"),
                List.of("data5", "data6", "column")
        );
        List<String> requestedCol = csvReader.columnFromCsvData(csvData, "requestedCol");
        assertEquals(List.of("get", "this", "column"), requestedCol,
                "sequence extractor should get data of requested column");
    }

    @Test
    void extractColumnNotPresent() {
        List<List<String>> csvData = List.of(
                List.of("header", "row", "requestedCol"),
                List.of("data0", "data1", "get"),
                List.of("data2", "data4", "this"),
                List.of("data5", "data6", "column")
        );
        List<String> requestedCol = csvReader.columnFromCsvData(csvData, "notAColumn");
        assertEquals(Collections.EMPTY_LIST, requestedCol,
                "when column is not present, resulting sequence should be empty");
    }


    @Test
    void extractFromEmpty() {
        assertEquals(
                Collections.EMPTY_LIST,
                csvReader.columnFromCsvData(Collections.emptyList(), "redundant"),
                "when data is empty, resulting sequence should be empty"
        );
    }

}