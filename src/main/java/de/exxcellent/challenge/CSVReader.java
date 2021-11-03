package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class CSVReader {

    /**
     * reads content of a csv file
     *
     * @param filePath path to the csv file to be read
     * @return a list of lists of strings where each sublist represents a row in a csv file and the individual strings
     *         are the respective cells of that row
     * @throws IOException when I/O error on file handling occurrs
     * @throws InputMismatchException when content of read csv file does not meet expectations
     */
    public List<List<String>> read(String filePath) throws IOException, InputMismatchException {

        List<List<String>> lines = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int numCells = -1;

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] splitLine = line.split(",");
            if (numCells == -1) {
                numCells = splitLine.length;
            } else if (splitLine.length != numCells) {
                throw new InputMismatchException("encountered line with different number of fields than before");
            }
            lines.add(Arrays.asList(splitLine));
        }
        fileReader.close();
        return lines;
    }

    /**
     * extracts a column with given name from csv data in form of a nested list by getting the column's index based on
     * the first list item
     *
     * @param csvData nested list representing csv data
     * @param colName name of the column to extract
     * @return content of cells in the requested column as list of strings
     */
    public List<String> columnFromCsvData(List<List<String>> csvData, String colName) {
        return null;
    }


}
