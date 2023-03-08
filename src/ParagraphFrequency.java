/**
 * This class is used to create a Paragraph Frequency object that contains a
 * word
 * and its frequency.
 * It also implements the Comparable interface to compare the frequency of two
 * words.
 */
public class ParagraphFrequency implements Comparable<ParagraphFrequency> {
    String word;
    Integer frequency;

    // Constructor
    public ParagraphFrequency(String word, Integer frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    // getters and setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    // toString
    @Override
    public String toString() {
        return "ParagraphFrequency{" + "word = " + word + ", frequency = " + frequency + '}';
    }

    // compareTo method to compare the frequency of two words in the paragraph
    // and return the result
    @Override
    public int compareTo(ParagraphFrequency otherWord) {
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
