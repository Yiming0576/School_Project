package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * TextAnalyzer class is used to analyze a text file.
 * It provides methods to calculate the number of letters, frequency of each letter, number of words, longest and shortest word, number of vowels and consonants.
 */
public class TextAnalyzer {

    private String filename; // The name of the file to be analyzed
    private String text; // The text content of the file
    private String[] words; // The words in the text

    private String longestWord; // The longest word in the text
    private String shortestWord; // The shortest word in the text

    private HashMap<String, Integer> frequencyOfUnigrams; // The frequency of each word in the text
    private HashMap<String, Integer> frequencyOfBigrams; // The frequency of each bigram in the text
    private HashMap<String, Integer> frequencyOfTrigrams; // The frequency of each trigram in the text

    /**
     * Constructor for the TextAnalyzer class.
     * It reads the file and stores the text content in lowercase.
     * @throws FileNotFoundException if the file is not found
     */
    public TextAnalyzer() throws FileNotFoundException {
        this.filename = getFileName();  // Get the name of the file from the user
        try (Scanner fileReader = new Scanner(new File(filename))) {
            // Read the file, the reason not to close the Scanner is that
            // it is a resource that is managed by the try-with-resources statement,
            // which means it will be closed automatically when the try block is exited
            StringBuilder fileText = new StringBuilder(); // is better to use StringBuilder when you are concatenating strings in a loop compared to using the + operator
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                fileText.append(line);
            }
            this.text = fileText.toString().toLowerCase(); // Store the text content in lowercase
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Method to get the name of the file from the user.
     * @return the name of the file
     */
    public String getFileName() {
        String file;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the file : ");
        file = input.nextLine();
        input.close();
        return file;
    }

    /**
     * Method to calculate the number of letters in the text.
     * @param text the text to be analyzed
     *             Question 1
     */
    public void calcNumberOfLetters(String text) {
        String processedText = text.replaceAll("[^a-zA-Z]", "");
        // Remove all non-alphabetic characters by using regular expression,
        // more information about regular expression can be found here: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
        System.out.println("processed text: " + processedText);                         // for debugging purposes
        System.out.println("Length: " + processedText.length());                        // for debugging purposes
        int count = 0;
        for (int i = 0; i < processedText.length(); i++) {
            System.out.println("text.charAt(" + i + "): " + processedText.charAt(i));
            if (Character.isLetter(processedText.charAt(i))) {
                count++;
            }
        }

        int numberOfLetters = count;
        System.out.println("The number of letters in the text is: " + numberOfLetters); // for debugging purposes
    }

    /**
     * Method to calculate the frequency of each letter in the text.
     * @param text the text to be analyzed
     *             Question 2
     */
    public void frequencyOfLetters(String text) {
        String processedText = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        System.out.println("processed text: " + processedText);
        int[] freq = new int[26];
        for (int i = 0; i < processedText.length(); i++) {
            char character = text.charAt(i);
            if (Character.isLetter(character)) {
                System.out.print(character);
            }
        }
    }

    /**
     * Method to calculate the number of words in the text.
     * @return the number of words in the text
     *          Question 3
     */
    public int numberOfWords() {
        return words.length;
    }

    /**
     * Method to find the longest word in the text.
     * @return the longest word in the text
     *          Question 4-1
     */
    public String LongestWord() {
        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    /**
     * Method to find the shortest word in the text.
     * @return the shortest word in the text
     *          Question 4-2
     */
    public String ShortestWord() {
        String shortestWord = words[0];
        for (String word : words) {
            if (word.length() < shortestWord.length()) {
                shortestWord = word;
            }
        }
        return shortestWord;
    }

    /**
     * Method to calculate the frequency of each word in the text.
     * @return the frequency of each word in the text
     *         Question 5
     */
    public HashMap<String, Integer> frequencyOfWords() {
        for (String word : words) {
            if (frequencyOfUnigrams.containsKey(word)) {
                frequencyOfUnigrams.put(word, frequencyOfUnigrams.get(word) + 1);
            } else {
                frequencyOfUnigrams.put(word, 1);
            }
        }
        return frequencyOfUnigrams;
    }

    /**
     * Method to calculate the frequency of each bigram in the text.
     * @return the frequency of each bigram in the text
     *        Question 6
     */
    public HashMap<String, Integer> frequencyOfBigrams() {
        HashMap<String, Integer> frequencyOfBigrams = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String bigram = words[i] + " " + words[i + 1];
            if (frequencyOfBigrams.containsKey(bigram)) {
                frequencyOfBigrams.put(bigram, frequencyOfBigrams.get(bigram) + 1);
            } else {
                frequencyOfBigrams.put(bigram, 1);
            }
        }

        return frequencyOfBigrams;
    }

    /**
     * Method to calculate the number of vowels in the text.
     * @return the number of vowels in the text
     *        customized question
     */
    public int numberOfVowels() {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    /**
     * Method to calculate the number of consonants in the text.
     * @return the number of consonants in the text
     *        customized question
     */
    public int numberOfConsonants() {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u' && Character.isLetter(ch)) {
                count++;
            }
        }
        return count;
    }


//     =====================================  Getter and Setter Methods  =====================================

    /**
     * Getter for the words in the text.
     * @return the words in the text
     */
    public String[] getWords() {
        return words;
    }

    /**
     * Setter for the words in the text.
     * It splits the text into words and stores them in an array.
     * @return the words in the text
     */
    public void setWords() {
        this.words = text.replaceAll("[^a-zA-Z ]", "").split("\\s+");
    }

    /**
     * Setter for the filename.
     * @param filename the filename to be set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the text.
     * @return the text in lowercase
     */
    public String getText() {
        return text;
    }
}