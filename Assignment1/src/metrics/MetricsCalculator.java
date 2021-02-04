package wordPlay.metrics;

import wordPlay.util.Results;
import java.text.DecimalFormat;

public class MetricsCalculator {
    private int wordsInInputFile = 0;
    private int numberOfSentences = 0;
    private int wordsInInputFile1 = 0;
    private int numberOfSentences1 = 0;
    private double totalWordLength = 0.0;
    private final DecimalFormat decimalFormat;
    private final Results results;

    /**
     *Implements functions for calculating Average words per sentence and average word length.
     *
     * @param fileNameIn Input file for metric calculation
     * @param resultsIn Results class object for printing the output to the output file and standard output.
     */

    public MetricsCalculator(String fileNameIn, Results resultsIn){
        results = resultsIn;
        decimalFormat = new DecimalFormat("#.0#");
    }
    @Override
    public String toString(){
        return "Calculates the metrics for the given input";
    }

    /**
     * Calculates average number of words per sentence.
     *
     * @param currentWord Input word using the helper class
     */
    public void averageWordsPerSentence(String currentWord) {
            wordsInInputFile++;
            if (currentWord.contains(".")) {
                numberOfSentences++;
            }
    }

    /**
     * Calculates the average word length in a given input file
     *
     * @param currentWordParam Input word using the helper class
     */
    public void averageWordLengthCalculator(String currentWordParam) {
            wordsInInputFile1++;
            totalWordLength += currentWordParam.length();
            if (currentWordParam.contains(".")){
                numberOfSentences1++;
            }
    }

    /**
     * Stores the metrics calculated by avgWordsPerSentence() and avgWordLengthCalculator() into a string builder using
     * results class object. Also formats the metrics results into required format.
     */
    public void storeMetricResults(){
        double avgWordsInSentence = (double) wordsInInputFile / numberOfSentences;
        String formattedAvgWords = decimalFormat.format(avgWordsInSentence);
        String avgWords = "AVG_NUM_WORDS_PER_SENTENCE = " + formattedAvgWords + "\n";
        results.storeOutputMetricsCalculator(avgWords);
        double avgWordLength = ((totalWordLength - numberOfSentences1) / wordsInInputFile1);
        String formattedAvgWordLength = decimalFormat.format(avgWordLength);
        String avgLength = "AVG_WORD_LENGTH = " + formattedAvgWordLength;
        results.storeOutputMetricsCalculator(avgLength);
    }
}
