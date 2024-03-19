package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests for format
 */
class FormatTest {

  /**
   * Tests for formatDataFromSr
   */
  @Test
  void formatDataFromSr() {
    String srLine = "[[Duck?:::DUCK!~HARD]]";
    Question expectedQuestion = new Question("Duck?", "DUCK!", Difficulty.HARD);
    Question actualQuestion = Format.formatDataFromSr(srLine);
    assertEquals(expectedQuestion.getQuestion(), actualQuestion.getQuestion());
    assertEquals(expectedQuestion.getAnswer(), actualQuestion.getAnswer());
    assertEquals(expectedQuestion.getDifficulty(), actualQuestion.getDifficulty());
    Question expectedQuestionTwo = new Question("Duck?", "DUCK!", Difficulty.EASY);
    String srLineTwo = "[[Duck?:::DUCK!~EASY]]";
    Question actualQuestionTwo = Format.formatDataFromSr(srLineTwo);
    assertEquals(expectedQuestionTwo.getQuestion(), actualQuestionTwo.getQuestion());
    assertEquals(expectedQuestionTwo.getAnswer(), actualQuestionTwo.getAnswer());
    assertEquals(expectedQuestionTwo.getDifficulty(), actualQuestionTwo.getDifficulty());
  }

  /**
   * Tests for formatData
   */
  @Test
  void formatData() {
    ArrayList<Question> questions = new ArrayList<>();
    questions.add(new Question("Duck?", "DUCK!", Difficulty.EASY));
    questions.add(new Question("Goose?", "GOOSE!", Difficulty.HARD));
    questions.add(new Question("DOG?", "Cat", Difficulty.EASY));

    StringBuilder questionData = new StringBuilder();
    questionData.append("[[Goose?:::GOOSE!~HARD]]").append(System.lineSeparator());
    questionData.append("[[Duck?:::DUCK!~EASY]]").append(System.lineSeparator());
    questionData.append("[[DOG?:::Cat~EASY]]").append(System.lineSeparator());

    StringBuilder realData = new StringBuilder();
    Format.formatData(questions, realData);

    assertEquals(questionData.toString(), realData.toString());
  }

  /**
   * Tests for the errors thrown
   */
  @Test
  void testErrors() {
    String invalidInput = "[[Monkey:::Ape!~no]]";
    String invalidInputTwo = "[[Monkey:::Ape!]]";
    assertThrows(IllegalArgumentException.class, () -> {
      Format.formatDataFromSr(invalidInput);
    });
    assertThrows(RuntimeException.class, () -> {
      Format.formatDataFromSr(invalidInputTwo);
    });
  }
}