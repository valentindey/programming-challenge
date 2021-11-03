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

}