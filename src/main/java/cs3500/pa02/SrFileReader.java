package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The srFileReader
 */
public class SrFileReader {

  private ArrayList<Question> allQuestions = new ArrayList<>();

  /**
   * Reads questions from the file path
   *
   * @param src The sr file path
   */
  public void readAllQuestions(Path src) {
    Scanner srFileScanner;
    try {
      srFileScanner = new Scanner(src);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    while (srFileScanner.hasNextLine()) {
      String text = srFileScanner.nextLine().trim();
      Question question = Format.formatDataFromSr(text);
      addQuestion(question);
    }
  }

  /**
   * Adds a question to the arrayList of questions
   *
   * @param question A question
   */
  private void addQuestion(Question question) {
    allQuestions.add(question);
  }

  /**
   * Gets the allQuestion field
   *
   * @return The allQuestion field
   */
  public ArrayList<Question> getAllQuestions() {
    return allQuestions;
  }
}
