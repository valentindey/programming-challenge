package de.exxcellent.challenge;

import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // CLI arguments may easily be adjusted to allow different columns etc.
        if (args[0].equals("--football")) {
            String teamWithSmallestGoalSpread = getMinDiffName(
                    args[1], "Team", "Goals", "Goals Allowed"
            );
            System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
        } else if (args[0].equals("--weather")) {
            String dayWithSmallestTempSpread = getMinDiffName(
                    args[1], "Day", "MxT", "MnT"
            );
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        } else {
            System.out.println("Input not understood, please provide two arguments: --football/--weather <CSV_FILE_NAME>");
        }
    }

    /**
     * wires together different components to solve the challenge
     *
     * @param csvFilePath path to input csv file
     * @param nameCol     name of column containing item names, used to get the final value output
     * @param firstCol    name of first number column
     * @param secondCol   name of second number column
     * @return the name (from `nameCol`) with the minimum absolute difference in corresponding items in the other
     * columns (`firstCol` and `secondCol`)
     */
    public static String getMinDiffName(String csvFilePath, String nameCol, String firstCol, String secondCol) {
        CSVReader csvReader = new CSVReader();
        DataService dataService = new DataService();
        String minDiffName = "";
        try {
            List<List<String>> csvData = csvReader.read(csvFilePath);
            List<String> nameColData = csvReader.columnFromCsvData(csvData, nameCol);
            List<Double> firstColData = dataService.listOfStringsToDouble(csvReader.columnFromCsvData(csvData, firstCol));
            List<Double> secondColData = dataService.listOfStringsToDouble(csvReader.columnFromCsvData(csvData, secondCol));
            List<Double> diffList = dataService.absDiffList(firstColData, secondColData);
            minDiffName = nameColData.get(dataService.argMin(diffList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minDiffName;
    }
}
