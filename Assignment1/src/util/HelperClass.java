    package wordPlay.util;

import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

/**
 * Utility to read the input using FileProcessor so that it can be used by other classes (WordRotator
 *      and MetricsCalculator)
 *
 * @author Rashmi Badadale
 */

public class HelperClass {
    private final String filename;
    private final WordRotator wordRotator;
    private final MetricsCalculator metricsCalculator;
    private FileProcessor fileprocessor;

    /**
     * Constructor for initializing the input file and instances of WordRotator & MetricsCalculator class.
     *
     * @param fileNameIn          Input Filename
     * @param wordRotatorIn       WordRotator class instance
     * @param metricsCalculatorIn MetricsCalculator class instance
     */

    public HelperClass(String fileNameIn, WordRotator wordRotatorIn, MetricsCalculator metricsCalculatorIn) {
        filename = fileNameIn;
        wordRotator = wordRotatorIn;
        metricsCalculator = metricsCalculatorIn;
    }

    /**
     * Overriding the toString() method
     */
    @Override
    public String toString() {
        return "Takes the polled input from FileProcessor and sends it for processing for" +
                " WordRotator and MetricsCalculator";
    }

    /**
     * Reads input from the FileProcessor poll method and forwards to the WordRotator and MetricsCalculator classes for
     * processing
     *
     * @throws IOException On any I/O errors while reading lines from input file
     */

    public void retrieveInput() throws IOException {
        String currentWord = null;
        try {
            fileprocessor = new FileProcessor(filename);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            System.err.println("File not found!");
            System.exit(0);
        } catch (InvalidPathException | IOException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (SecurityException e) {
            System.err.println("File permission denied");
            e.printStackTrace();
            System.exit(0);
        }

        try {
            File file = new File(filename);
            if (0 == file.length()) {
                throw new IllegalArgumentException("Empty Input File!");
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            System.exit(0);
        }
        currentWord = fileprocessor.poll();
        while (null != currentWord) {
            try {
                if (!currentWord.matches("^[a-zA-Z0-9(.)?]*$")) {
                    throw new IllegalArgumentException("Input contains character other than [a-zA-Z0-9]");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.exit(0);
            }
            wordRotator.wordToRotate(currentWord);
            metricsCalculator.averageWordsPerSentence(currentWord);
            metricsCalculator.averageWordLengthCalculator(currentWord);
            currentWord = fileprocessor.poll();
        }
    }
}

