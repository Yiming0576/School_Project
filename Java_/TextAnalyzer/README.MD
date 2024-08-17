# TextAnalyzer

## Overview

`TextAnalyzer` is a Java class used to analyze a text file and provide various metrics about its content. The class offers functionality to calculate the number of letters, the frequency of each letter, the number of words, and identify the longest and shortest words, as well as count vowels and consonants.

## Features

- **Calculate the Number of Letters**: Counts all alphabetic characters in the text.
- **Frequency of Each Letter**: Calculates the frequency of each letter in the text.
- **Number of Words**: Counts the total number of words in the text.
- **Longest and Shortest Words**: Identifies the longest and shortest words in the text.
- **Frequency of Each Word**: Calculates the frequency of each word in the text.
- **Frequency of Bigrams**: Calculates the frequency of each pair of consecutive words (bigrams) in the text.
- **Number of Vowels**: Counts the number of vowel characters in the text.
- **Number of Consonants**: Counts the number of consonant characters in the text.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Basic understanding of Java and file handling

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-repo/text-analyzer.git
   ```
2. **Navigate to the Project Directory**:

   ```bash
   cd text-analyzer
   ```
3. **Compile the Code**:

   ```bash
   javac -d bin src/org/example/TextAnalyzer.java
   ```

## Usage

1. **Create an Instance of `TextAnalyzer`**:

   Instantiate the `TextAnalyzer` class, which will prompt you to enter the name of the file you wish to analyze.

   ```java
   TextAnalyzer analyzer = new TextAnalyzer();
   ```
2. **Set Words**:

   After instantiation, you can split the text into words.

   ```java
   analyzer.setWords();
   ```
3. **Call Analysis Methods**:

   Use the methods provided to perform various analyses.

   - **Calculate Number of Letters**:

     ```java
     analyzer.calcNumberOfLetters(analyzer.getText());
     ```
   - **Frequency of Letters**:

     ```java
     analyzer.frequencyOfLetters(analyzer.getText());
     ```
   - **Get Number of Words**:

     ```java
     int numWords = analyzer.numberOfWords();
     ```
   - **Find Longest Word**:

     ```java
     String longest = analyzer.LongestWord();
     ```
   - **Find Shortest Word**:

     ```java
     String shortest = analyzer.ShortestWord();
     ```
   - **Frequency of Words**:

     ```java
     HashMap<String, Integer> wordFreq = analyzer.frequencyOfWords();
     ```
   - **Frequency of Bigrams**:

     ```java
     HashMap<String, Integer> bigramFreq = analyzer.frequencyOfBigrams();
     ```
   - **Number of Vowels**:

     ```java
     int numVowels = analyzer.numberOfVowels();
     ```
   - **Number of Consonants**:

     ```java
     int numConsonants = analyzer.numberOfConsonants();
     ```

## Example

Here's a simple example demonstrating how to use the `TextAnalyzer` class:

```java
import org.example.TextAnalyzer;

public class Main {
    public static void main(String[] args) {
        try {
            TextAnalyzer analyzer = new TextAnalyzer();
            analyzer.setWords();
            System.out.println("Number of Words: " + analyzer.numberOfWords());
            System.out.println("Longest Word: " + analyzer.LongestWord());
            System.out.println("Shortest Word: " + analyzer.ShortestWord());
            System.out.println("Number of Vowels: " + analyzer.numberOfVowels());
            System.out.println("Number of Consonants: " + analyzer.numberOfConsonants());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
```

## 

## Code Explanation

* **Constructor**: Reads the file specified by the user and stores its content in lowercase.
* **Get File Name**: Prompts the user to enter the file name.
* **Calculate Number of Letters**: Counts and displays the number of alphabetic characters.
* **Frequency of Letters**: (Not fully implemented) Plans to calculate letter frequencies.
* **Number of Words**: Returns the total word count.
* **Longest and Shortest Words**: Finds and returns the longest and shortest words respectively.
* **Frequency of Words and Bigrams**: Calculates and returns the frequency of words and bigrams.
* **Number of Vowels and Consonants**: Counts and returns the number of vowels and consonants.


## License

This project is licensed under the MIT License. See the [LICENSE]() file for details.
