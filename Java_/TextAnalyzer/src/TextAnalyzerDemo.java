package org.example;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * The {@code TextAnalyzerDemo} class demonstrates how to use the {@code TextAnalyzer} class.
 * It includes example usage of various methods to analyze a text file.
 */
public class TextAnalyzerDemo {
    public static void main(String[] args) {
        try {
            // Create an instance of TextAnalyzer
            TextAnalyzer analyzer = new TextAnalyzer();
            
            // Set words from the loaded text
            analyzer.setWords();

            // Get the number of letters
            analyzer.calcNumberOfLetters(analyzer.getText());

            // Calculate and print frequency of letters
            analyzer.frequencyOfLetters(analyzer.getText());

            // Calculate and print number of words
            System.out.println("Number of words: " + analyzer.numberOfWords());

            // Find and print the longest word
            System.out.println("Longest word: " + analyzer.LongestWord());

            // Find and print the shortest word
            System.out.println("Shortest word: " + analyzer.ShortestWord());

            // Calculate and print the frequency of each word
            HashMap<String, Integer> wordFrequencies = analyzer.frequencyOfWords();
            System.out.println("Word frequencies: " + wordFrequencies);

            // Calculate and print the frequency of each bigram
            HashMap<String, Integer> bigramFrequencies = analyzer.frequencyOfBigrams();
            System.out.println("Bigram frequencies: " + bigramFrequencies);

            // Calculate and print the number of vowels
            System.out.println("Number of vowels: " + analyzer.numberOfVowels());

            // Calculate and print the number of consonants
            System.out.println("Number of consonants: " + analyzer.numberOfConsonants());
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
