package cs3500.pa02;

import java.io.IOException;

/**
 * The view for the application
 */
public class ConsoleDisplay {

  private final Appendable output;

  /**
   * Console Display Constructor
   *
   * @param output Appendable (Location for output)
   */
  ConsoleDisplay(Appendable output) {
    this.output = output;
  }

  /**
   * Appends the SourcePrompt question to output
   */
  public void displaySourcePrompt() {
    try {
      output.append("Let's get studying!");
      output.append(System.lineSeparator());
      output.append("To start please provide a .sr path and enter");
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid Output");
    }
  }

  /**
   * Appends question count prompt to the output
   */
  public void displayQuestionCountPrompt() {
    try {
      output.append("How many questions would you like to study?");
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid Output");
    }
  }

  /**
   * Appends question and option choices to output
   *
   * @param question The question part of the question
   */
  public void displayQuestion(String question) {
    try {
      output.append("Question: " + question);
      output.append(System.lineSeparator());
      output.append("Options: (1) Quit , (2) Hard , (3) Easy , (4) Show Answer");
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid Output");
    }
  }

  /**
   * Appends answer and option choices to output
   *
   * @param answer The answer part of the question
   */
  public void displayAnswer(String answer) {
    try {
      output.append("Answer: " + answer);
      output.append(System.lineSeparator());
      output.append("Options: (1) Quit , (2) Hard , (3) Easy, (4) Show Answer Again");
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid Output");
    }
  }

  /**
   * Appends the stats StringBuilder to output
   *
   * @param stats A formatted stats StringBuilder
   */
  public void displayStats(StringBuilder stats) {
    try {
      output.append(stats);
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid Output");
    }
  }

  /**
   * Appends the errorMessage to output
   *
   * @param errorMessage The error message to be outputted
   */
  public void displayInputError(String errorMessage) {
    try {
      output.append(errorMessage);
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid Output");
    }
  }
}
