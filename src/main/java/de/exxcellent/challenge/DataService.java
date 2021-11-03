package de.exxcellent.challenge;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataService {

    /**
     * converts a list of strings into a list of doubles
     *
     * @param input input list of strings
     * @return list of doubles corresponding to the input strings
     * @throws NumberFormatException when encountering at least one item that cannot be parsed to double
     */
    public List<Double> listOfStringsToDouble(List<String> input) throws NumberFormatException {
        return input.stream().map(Double::parseDouble).collect(Collectors.toList());
    }

    /**
     * element wise calculation of absolute differences of two lists of numbers
     *
     * @param list1 first list of numbers
     * @param list2 second list of numbers
     * @return list containing the absolute difference of corresponding items in the input lists
     */
    public List<Double> absDiffList(List<Double> list1, List<Double> list2) {
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("passed lists must be of the same size!");
        }
        return IntStream.range(0, list1.size())
                .mapToDouble(i -> Math.abs(list1.get(i) - list2.get(i)))
                .boxed().collect(Collectors.toList());
    }

    /**
     * gets the index of the smallest item in a list of numbers
     *
     * @param list list to find the smallest item in
     * @return index of the smallest item in the provided list, -1 when input is empty
     */
    public int argMin(List<Double> list) {
        if (list.isEmpty()) {
            return -1;
        }
        Double minDiff = Collections.min(list);
        return list.indexOf(minDiff);
    }

}
