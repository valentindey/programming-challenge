package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class CSVReader {
    public List<List<String>> read(String filePath) throws IOException, InputMismatchException {

        List<List<String>> lines = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int numFields = -1;

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] splitLine = line.split(",");
            if (numFields == -1) {
                numFields = splitLine.length;
            } else if (splitLine.length != numFields) {
                throw new InputMismatchException("encountered line with different number of fields than before");
            }
            lines.add(Arrays.asList(splitLine));
        }
        fileReader.close();
        return lines;
    }
}
