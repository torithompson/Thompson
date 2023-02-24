/**
 * This class represents a word frequency object with a word and a frequency. It
 * implements the Comparable interface for sorting purposes.
 */
public class WordFrequency implements Comparable<WordFrequency> {
    String word;
    Integer frequency;

    // Constructor WordFrequency(String word, Integer frequency)
    public WordFrequency(String word, Integer frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    // Returns the word of the WordFrequency object.
    public String getWord() {
        return word;
    }

    // Returns the frequency of the WordFrequency object.
    public Integer getFrequency() {
        return frequency;
    }

    // Sets the word of the WordFrequency object with the given string.
    public void setWord(String word) {
        this.word = word;
    }

    // Sets the frequency of the WordFrequency object with the given integer.
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    /**
     * This method overrides the toString method of the Object class. It returns a
     * String representation of the WordFrequency object.
     * 
     * @return
     */
    @Override
    public String toString() {
        return "WordFrequency{" + "word = " + word + ", frequency = " + frequency + '}';
    }

    /**
     * Compares two WordFrequency objects based on their frequency. It returns -1,
     * 0, or 1, depending on whether the frequency of this WordFrequency object is
     * less than, equal to, or greater than the frequency of the other WordFrequency
     * object.
     * 
     * @param otherWord
     * @return
     */
    @Override
    public int compareTo(WordFrequency otherWord) {
        int result = 2;
        if (this.frequency < otherWord.frequency) {
            result = -1;
        } else if (this.frequency > otherWord.frequency) {
            result = 1;
        } else if (this.frequency == otherWord.frequency) {
            result = 0;
        }
        return result;
    }
}
