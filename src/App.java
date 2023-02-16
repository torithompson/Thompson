import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * This program reads a file and counts the number of times each word appears in the file. The program then creates an HTML file with the word count.
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ArrayList<String> words = readWords("res/words.txt");
        HashMap<String, Integer> wordCount = buildHashmap(words);
        createHTMLFile(wordCount);
    }

    /*
     * Read the file and return an ArrayList of words
     * 
     * @param fileName
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
     * Build a HashMap of words and their count
     * 
     * @param words
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
     * Create an HTML file with the word count and create a html file with the Word
     * Count table in it
     * 
     * @param wordCount
     */
    private static void createHTMLFile(HashMap<String, Integer> wordCount) {
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

        for (String keyWord : wordCount.keySet()) {
            System.out.println(keyWord + ": " + wordCount.get(keyWord));
        }
    }
}