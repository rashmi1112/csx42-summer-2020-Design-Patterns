package wordPlay.util;

import wordPlay.metrics.MetricsCalculator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private final String fileName;
    private String word;
    private MetricsCalculator metricsCalculator;
    private final StringBuilder stringBuilder1;
    private final StringBuilder stringBuilder2;

    /**
     * Results stores the individual processed input from WordRotator and MetricsCalculator classes' methods
     * and stores it into a StringBuilder object and prints the output on standard output
     * and output file.
     *
     *
     * @param fileNameIn Output Filename
     */
    public Results(String fileNameIn) {
        fileName = fileNameIn;
        stringBuilder1 =  new StringBuilder();
        stringBuilder2 =  new StringBuilder();
    }


    /**
     * Overriding the toString() method
     */
    @Override
    public String toString() {
        return " Stores and Prints the results to the output file and console";
    }

    /**
     * Stores the processed input from the wordToRotate() method into StringBuilder object
     *
     * @param word processed input from wordToRotate() method
     */

    public void storeOutputWordRotator(String word) {
        stringBuilder1.append(word);

    }

    /**
     * Stores the processed input from the printResultMetrics() method into StringBuilder object
     *
     * @param word processed input from printResultMetrics() method
     */

    public void storeOutputMetricsCalculator(String word) {
        stringBuilder2.append(word);
    }

    /**
     * writeToFile() creates an object of BufferWriter class and write the contents of
     * stringBuilder object into it
     */
    @Override
    public void writeToFile() {

        try {
            BufferedWriter writerObject = new BufferedWriter(new FileWriter(fileName));
            writerObject.write(stringBuilder1.toString());
            writerObject.write(stringBuilder2.toString());
            writerObject.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();

            System.exit(0);
        }
    }

    /**
     * Calls functions for printing the result on the console and the output file.
     */

    public void printResult() {
        writeToFile();
        writeToStdOut();
    }

    /**
     * Function to write the Results on the console.
     */

    @Override
    public void writeToStdOut() {
        System.out.print(stringBuilder1.toString());
        System.out.print(stringBuilder2.toString());
    }


}


