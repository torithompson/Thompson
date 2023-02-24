import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 
 * The main class reads a file of
 * words, counts the frequency of each word,
 * and outputs the results to two HTML files - one sorted by frequency and the
 * other unsorted.
 */
public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<String> words = readWords("res/words.txt");
        HashMap<String, Integer> wordCount = buildHashmap(words);
        createHTMLFileHashmap(wordCount);
        ArrayList<WordFrequency> frequency = buildWordFrequencyList(wordCount);
        Collections.sort(frequency);
        createHTMLFileArray(frequency);
    }

    /**
     * 
     * Reads words from a file and returns them as an ArrayList. The method takes a
     * String parameter containing the file name to read from. It reads the file and
     * processes it line by line, splitting each line into individual words and
     * adding them to an ArrayList. Any leading or trailing spaces are removed, and
     * all words are converted to lowercase. The resulting ArrayList is returned. If
     * the file cannot be found or there is an I/O exception, it will print the
     * stack trace.
     * 
     * @param fileName
     * @return
     */
    private static ArrayList<String> readWords(String fileName) {
        File file = new File(fileName);
        ArrayList<String> wordList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] words = line.split("[ .,]+");
                for (String word : words) {
                    if (word.trim().length() > 0) {
                        wordList.add(word.toLowerCase());
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    /**
     * 
     * Constructs a HashMap with word count frequencies from an ArrayList of words.
     * The method takes an ArrayList of String containing the words to count. The
     * method iterates through the ArrayList, adding each word to a HashMap and
     * incrementing its count each time it appears. If the word already exists in
     * the HashMap, its count is incremented by 1. Otherwise, a new entry is added
     * with a count of 1. The resulting HashMap is returned.
     * 
     * @param words
     * @return
     */
    private static HashMap<String, Integer> buildHashmap(ArrayList<String> words) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            Integer count = wordCount.get(word);
            if (count == null) {
                wordCount.put(word, 1);
            } else {
                wordCount.put(word, count + 1);
            }
        }
        return wordCount;
    }

    /**
     * 
     * Generates an HTML file named "wordCount.html" displaying the word count
     * frequency using a HashMap of words and their frequency count. The method
     * creates a table with two columns: one for the word and one for its frequency
     * count. The data for the table is obtained from the HashMap passed as a
     * parameter. If
     * there is an I/O exception, it will print the stack trace.
     * 
     * @param wordCount
     */
    private static void createHTMLFileHashmap(HashMap<String, Integer> wordCount) {
        File file = new File("res/wordCount.html");

        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            builder.append("<!DOCTYPE html>");
            builder.append("<html>");
            builder.append("<head>");
            builder.append("<title>Word Count</title>");
            builder.append("<link rel ='stylesheet' href='../res/style.css'>");
            builder.append("</head>");
            builder.append("<body>");
            builder.append("<h1>Word Count</h1>");
            builder.append("<table>");
            for (String key : wordCount.keySet()) {
                builder.append("<tr>");
                builder.append("<td>" + key + "</td>");
                builder.append("<td>" + wordCount.get(key) + "</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");
            builder.append("</body>");
            builder.append("</html>");
            fileWriter.append(builder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method converts a HashMap of words and their frequency count to an
     * ArrayList of WordFrequency objects. It iterates through the HashMap keys,
     * creates a new WordFrequency object for each key-value pair and adds it to the
     * ArrayList. The resulting ArrayList is returned.
     * 
     * @param wordCount
     * @return
     */
    public static ArrayList<WordFrequency> buildWordFrequencyList(HashMap<String, Integer> wordCount) {
        ArrayList<WordFrequency> wordFrequencyList = new ArrayList<>();
        for (String key : wordCount.keySet()) {
            wordFrequencyList.add(new WordFrequency(key, wordCount.get(key)));
        }
        return wordFrequencyList;
    }

    /**
     * 
     * Creates an HTML file named sorted.html that displays the word frequency
     * count. The method takes an ArrayList of WordFrequency objects that contain
     * the words and their corresponding frequency count. The method creates the
     * HTML file and includes a CSS file. The method then
     * creates a table with two columns: one for the word and one for its frequency
     * count. The data for the table is obtained from the ArrayList passed as a
     * parameter. If there is an I/O exception, it will print the stack trace.
     * 
     * @param frequency
     */
    private static void createHTMLFileArray(ArrayList<WordFrequency> frequency) {
        File file = new File("res/sorted.html");

        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            builder.append("<!DOCTYPE html>");
            builder.append("<html>");
            builder.append("<head>");
            builder.append("<title>Word Count</title>");
            builder.append("<link rel ='stylesheet' href='../res/style.css'>");
            builder.append("</head>");
            builder.append("<body>");
            builder.append("<h1>Word Count</h1>");
            builder.append("<table>");
            for (WordFrequency wordFrequency : frequency) {
                builder.append("<tr>");
                builder.append("<td>" + wordFrequency.getWord() + "</td>");
                builder.append("<td>" + wordFrequency.getFrequency() + "</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");
            builder.append("</body>");
            builder.append("</html>");
            fileWriter.append(builder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}