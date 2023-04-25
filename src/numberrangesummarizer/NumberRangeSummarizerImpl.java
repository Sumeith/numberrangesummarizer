package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{

    private static final Logger logger = Logger.getLogger(NumberRangeSummarizerImpl.class.getName());

    /**
     * Collects a comma delimited string of integers and converts it to a collection of integers
     * Example: "1,3,6,7,8,12,13,14,15,21,22,23,24,31" => [1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31]
     *
     * @param input a comma delimited string of integers
     * @return a collection of integers
     */
    @Override
    public Collection<Integer> collect(String input) {
        logger.log(Level.INFO, "Attempting to collect numbers");
        List<Integer> numbers = new ArrayList<>();

        if (input == null || input.isEmpty()) {
            return numbers;
        }

        String[] parts = input.split(",");
        for (String part : parts) {
            if (part.contains("-")) {
                String[] rangeParts = part.split("-");
                if (rangeParts.length == 2) {
                    try {
                        int start = Integer.parseInt(rangeParts[0].trim());
                        int end = Integer.parseInt(rangeParts[1].trim());
                        for (int i = start; i <= end; i++) {
                            numbers.add(i);
                        }
                    } catch (NumberFormatException e) {
                        logger.log(Level.WARNING, "Invalid Number Format");
                        return null;
                    }
                } else {
                    // ignore invalid range format
                    logger.log(Level.WARNING, "Invalid Range Format");
                    return null;
                }
            } else {
                try {
                    int number = Integer.parseInt(part.trim());
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "Invalid Number Format");
                    return null;
                }
            }
        }
        logger.log(Level.INFO, "Collection completed");
        return numbers;
    }

    /**
     * Takes a collection of integers and returns a comma delimited string of ranges
     * Example: [1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31] => "1, 3, 6-8, 12-15, 21-24, 31"
     *
     * @param input a collection of integers
     * @return a comma delimited string of ranges
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        logger.log(Level.INFO, "Attempting to summarize collection");

        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Integer> numbers = new ArrayList<>(input);

        List<String> ranges = new ArrayList<>();
        int start = numbers.get(0);
        int end = start;
        for (int i = 1; i <= numbers.size(); i++) {
            int number = i < numbers.size() ? numbers.get(i) : -1;
            if (number == end || number == end + 1) {
                end = number;
            } else {
                ranges.add(getRangeString(start, end));
                if (number != -1) {
                    start = number;
                    end = number;
                }
            }
        }
        logger.log(Level.INFO, "Summarizing of collection completed");
        return String.join(", ", ranges);
    }

    /**
     * Helper function for summarizeCollection() to get a range if sequential.
     *
     */
    private String getRangeString(int start, int end) {
        //if statement to check for duplicates
        if (start == end)
            return Integer.toString(start);
        return start + "-" + end;
    }
}
