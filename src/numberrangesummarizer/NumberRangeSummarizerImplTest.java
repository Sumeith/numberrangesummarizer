package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;
import java.util.Collection;

class NumberRangeSummarizerImplTest {
    /**
     * sampleInputTest()
     *
     * This test is to test the sample input provided from the interface
     * Example: [1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31] => "1, 3, 6-8, 12-15, 21-24, 31"
    * */
    @Test
    public void  sampleInputTest(){
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        Collection<Integer> input = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * testCollect()
     *
     * This test is to test the if a list can be created from a simple list with no sequential numbers.
     * Example: "1,3,6,7,8,12,13,14,15,21,22,23,24,31" => [1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31]
     * */
    @Test
    public void testCollect() {
        NumberRangeSummarizer nrs = new NumberRangeSummarizerImpl();
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        Collection<Integer> actual = nrs.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assertions.assertEquals(expected, actual);
    }

    /**
     * testSummarizeCollectionEmptyInput()
     *
     * This test ensures that an empty string is produced when the list is empty
     *
     * Example: "" => []
     * */
    @Test
    public void testSummarizeCollectionEmptyInput() {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        Collection<Integer> input = Arrays.asList();
        String expected = "";
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * testEmptyCollect()
     *
     * This test ensures that an empty list is produced when the string is empty
     *
     * Example: [] => ""
     * */
    @Test
    void testEmptyCollect() {
        NumberRangeSummarizerImpl nrs = new NumberRangeSummarizerImpl();
        Collection<Integer> result = nrs.collect("");
        Assertions.assertEquals(Arrays.asList(), result);
    }

    /**
     * testSummarizeCollectionSingleNumber()
     *
     * This test ensures that a string with 1 number is produced when the list contains one number
     *
     * Example: [5] => "5"
     * */
    @Test
    public void testSummarizeCollectionSingleNumber() {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        Collection<Integer> input = Arrays.asList(5);
        String expected = "5";
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * testSummarizeCollectionSingleNumber()
     *
     * This test ensures that a string with 1 number is produced when the list contains one number
     *
     * Example: "5" => [5]
     * */
    @Test
    public void testCollectSingleNumber() {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        String input = "5";
        Collection<Integer> actual = summarizer.collect(input);
        Assertions.assertEquals(Arrays.asList(5), actual);
    }

    /**
     * testSummarizeCollectionSingleRange()
     *
     * This test ensures that a string with ranges are produced and ignores duplicates when the list contains sequential numbers with duplicates inbetween
     *
     * Example: [1,2,3,4,5] => "1-5"
     * */
    @Test
    public void testSummarizeCollectionSingleRange() {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        Collection<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        String expected = "1-5";
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * testSummarizeCollectionSingleRange()
     *
     * This test ensures that a string with ranges are produced and ignores duplicates when the list contains sequential numbers with duplicates inbetween
     *
     * Example: "1-5" => [1,2,3,4,5]
     * */
    @Test
    public void testCollectSingleRange() {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        String input = "1-5";
        Collection<Integer> actual = summarizer.collect(input);
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5), actual);
    }

    /**
     * testSummarizeCollectionMultipleRanges()
     *
     * This test ensures that a string with ranges are produced when the list contains sequential numbers
     *
     * Example: [1, 3, 4, 5, 6, 7, 10, 11] => "1, 3-7, 10-11"
     * */
    @Test
    public void testSummarizeCollectionMultipleRanges() {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        Collection<Integer> input = Arrays.asList(1, 3, 4, 5, 6, 7, 10, 11);
        String expected = "1, 3-7, 10-11";
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * testCollectMultipleRanges()
     *
     * This test ensures that a list is produced when where the string contains several ranges
     * Example: "1, 3-7, 10-11" => [1, 3, 4, 5, 6, 7, 10, 11]
     * */
    @Test
    public void testCollectMultipleRanges() {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        String input = "1, 3-7, 10-11";
        Collection<Integer> actual = summarizer.collect(input);
        Assertions.assertEquals(Arrays.asList(1, 3, 4, 5, 6, 7, 10, 11), actual);
    }

    /**
     * testCollectMultipleRanges()
     *
     * This test ensures that a list is produced when where the string contains several ranges and
     * the ranges are not ordered/sorted
     * Example: [1,2,7,8,9,4,5] => "1-2, 7-9, 4-5"
     * */
    @Test
    void testSummarizeMixedRanges() {
        NumberRangeSummarizerImpl nrs = new NumberRangeSummarizerImpl();
        String result = nrs.summarizeCollection(Arrays.asList(1,2,7,8,9,4,5));
        Assertions.assertEquals("1-2, 7-9, 4-5", result);
    }

    /**
     * testCollectMixedRanges()
     *
     * This test ensures that a string is produced when where the list contains several ranges and
     * the ranges are not ordered/sorted
     * Example: "1-2, 7-9, 4-5" => [1,2,7,8,9,4,5]
     * */
    @Test
    void testCollectMixedRanges() {
        NumberRangeSummarizerImpl nrs = new NumberRangeSummarizerImpl();
        Collection<Integer> result = nrs.collect("1-2, 7-9, 4-5");
        Assertions.assertEquals(Arrays.asList(1,2,7,8,9,4,5), result);
    }

    /**
     * testSummarizeNonConsecutiveNumbers()
     *
     * This test ensures that a string with numbers are produced when there are no sequential numbers
     *
     * Example: [1,3,5,7,9,13] => "1, 3, 5, 7, 9, 13"
     * */
    @Test
    void testSummarizeNonConsecutiveNumbers() {
        NumberRangeSummarizerImpl nrs = new NumberRangeSummarizerImpl();
        String result = nrs.summarizeCollection(Arrays.asList(1,3,5,7,9,13));
        Assertions.assertEquals("1, 3, 5, 7, 9, 13", result);
    }

    /**
     * testSummarizeNonConsecutiveNumbers()
     *
     * This test ensures that a string with numbers are produced when there are no sequential numbers
     *
     * Example: "1, 3, 5, 7, 9, 13" => [1,3,5,7,9,13]
     * */
    @Test
    void testCollectNonConsecutiveNumbers() {
        NumberRangeSummarizerImpl nrs = new NumberRangeSummarizerImpl();
        Collection<Integer> result = nrs.collect("1, 3, 5, 7, 9, 13");
        Assertions.assertEquals(Arrays.asList(1,3,5,7,9,13), result);
    }

    /**
     * testSummarizeCollectionRepeatedNumbers()
     *
     * This test ensures that a string with ranges are produced and ignores duplicates when the list contains sequential numbers with duplicates inbetween
     *
     * Example: [1, 1, 2, 2, 3, 3, 3] => "1-3"
     * */
    @Test
    public void testSummarizeCollectionDuplicatesNumbers() {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        Collection<Integer> input = Arrays.asList(1, 1, 2, 2, 3, 3, 3);
        String expected = "1-3";
        String actual = summarizer.summarizeCollection(input);
        Assertions.assertEquals(expected, actual);
    }


    /**
     * testNonNumericCollect()
     *
     * If there is a non-numeric element in the string, null is returned
     *
     * "1,2,3,four,5" => null
     * */
    @Test
    void testNonNumericCollect() {
        NumberRangeSummarizerImpl nrs = new NumberRangeSummarizerImpl();
        Collection<Integer> result = nrs.collect("1,2,3,four,5");
        Assertions.assertEquals(null, result);
    }

}