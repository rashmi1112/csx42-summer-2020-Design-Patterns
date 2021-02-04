package wordPlay.handler;

import wordPlay.util.Results;

/**
 * The WordRotator class takes the input from the provided file and rotates the word by mentioned index number.
 *
 * @author Rashmi Badadale
 */

public class WordRotator {
    private final Results results;
    private int wordIndex = 0;

    /**
     * Constructor to initialize result instance.
     *
     * @param resultsIn Object of the Results class
     */

    public WordRotator(Results resultsIn) {
        results = resultsIn;
    }

    /**
     * Overriding the toString() method
     */
    @Override
    public String toString(){
        return "Rotates each word in the input file";
    }

    /**
     * Rotates the current word to the right by x positions, where x is the index of word in the sentence.
     *
     * @param currentWordParam Input word using the helper class
     * @exception IllegalArgumentException When inout file contains an empty line.
     */
    public void wordToRotate(String currentWordParam) throws IllegalArgumentException {
        String rotatedWord;
        String rotatedWordWithoutPeriod;
        int rotatingIndex;
        String rotatedWordTemp;
        int neutralizingIndex;

            wordIndex++;
            try{
                if(0 == currentWordParam.length()){
                    throw new IllegalArgumentException("Input file contains an empty line!");
                }
            } catch (IllegalArgumentException illegalArgumentException){
                illegalArgumentException.printStackTrace();
                System.exit(0);
            }

            if (currentWordParam.contains(".")) {
                String truncatedWord = currentWordParam.substring(0, (currentWordParam.length() - 1));
                neutralizingIndex = wordIndex % truncatedWord.length();
                rotatingIndex = truncatedWord.length() - neutralizingIndex;
                rotatedWordWithoutPeriod = truncatedWord.substring(rotatingIndex) + truncatedWord.substring(0, rotatingIndex);
                rotatedWord = rotatedWordWithoutPeriod + "." + "\n";
               wordIndex = 0;

            } else {
                neutralizingIndex = wordIndex % currentWordParam.length();
                rotatingIndex = currentWordParam.length() - neutralizingIndex;
                rotatedWordTemp = currentWordParam.substring(rotatingIndex) + currentWordParam.substring(0, rotatingIndex);
                rotatedWord = rotatedWordTemp + " ";
            }

            results.storeOutputWordRotator(rotatedWord);
    }
}


