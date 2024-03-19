package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * ConsoleDisplay Tests
 */
public class ConsoleDisplayTest {
  StringBuilder output;
  ConsoleDisplay consoleDisplay;

  /**
   * Initialization of test data
   */
  @BeforeEach
  void initDisplay() {
    output = new StringBuilder();
    consoleDisplay = new ConsoleDisplay(output);
  }

  /**
   * Tests for displaySourcePrompt
   */
  @Test
  public void testDisplaySourcePrompt() {
    consoleDisplay.displaySourcePrompt();
    String expectedOutput = "Let's get studying!"
        + System.lineSeparator()
        + "To start please provide a .sr path and enter";
    assertEquals(expectedOutput.trim(), output.toString().trim());
  }

  /**
   * Tests for displayQuestionCountPrompt
   */
  @Test
  public void testDisplayQuestionCountPrompt() {
    consoleDisplay.displayQuestionCountPrompt();
    String expectedOutput = "How many questions would you like to study?"
        + System.lineSeparator();
    assertEquals(expectedOutput.trim(), output.toString().trim());
  }

  /**
   * Tests for displayQuestion
   */
  @Test
  public void testDisplayQuestion() {
    String question = "Ducks?";
    consoleDisplay.displayQuestion(question);
    String expectedOutput = "Question: " + question + System.lineSeparator()
        + "Options: (1) Quit , (2) Hard , (3) Easy , (4) Show Answer" + System.lineSeparator();
    assertEquals(expectedOutput.trim(), output.toString().trim());
  }

  /**
   * Tests for displayAnswer
   */
  @Test
  public void testDisplayAnswer() {
    String answer = "DUCKS!";
    consoleDisplay.displayAnswer(answer);
    String expectedOutput = "Answer: " + answer + System.lineSeparator()
        + "Options: (1) Quit , (2) Hard , (3) Easy, (4) Show Answer Again" + System.lineSeparator();
    assertEquals(expectedOutput.trim(), output.toString().trim());
  }

  /**
   * Tests for displayStats
   */
  @Test
  public void testDisplayStats() {
    StringBuilder stats = new StringBuilder();
    stats.append("Number of Questions Answered: 10" + System.lineSeparator());
    stats.append("Number of Questions Changed From Easy To Hard: 2" + System.lineSeparator());
    consoleDisplay.displayStats(stats);
    String expectedOutput = "Number of Questions Answered: 10"
        + System.lineSeparator()
        + "Number of Questions Changed From Easy To Hard: 2"
        + System.lineSeparator();
    assertEquals(expectedOutput.trim(), output.toString().trim());
  }

  /**
   * Tests for displayInputError
   */
  @Test
  public void testDisplayInputError() {
    String errorMessage = "Invalid input. Please try again.";
    consoleDisplay.displayInputError(errorMessage);
    String expectedOutput = "Invalid input. Please try again." + System.lineSeparator();
    assertEquals(expectedOutput.trim(), output.toString().trim());
  }
}
