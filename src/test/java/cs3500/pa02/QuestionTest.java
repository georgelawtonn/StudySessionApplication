package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the question class
 */
class QuestionTest {
  Question question;

  /**
   * Initialization of test data
   */
  @BeforeEach
  void initDuckQ() {
    question = new Question("DUCK", "DUCKS", Difficulty.EASY);
  }

  /**
   * Tests for getDifficulty
   */
  @Test
  void getDifficulty() {
    Difficulty actualDifficulty = question.getDifficulty();
    assertEquals(Difficulty.EASY, actualDifficulty);
  }

  /**
   * Tests for getAnswer
   */
  @Test
  void getAnswer() {
    String actualAnswer = question.getAnswer();
    assertEquals("DUCKS", actualAnswer);
  }

  /**
   * Tests for getQuestion
   */
  @Test
  void getQuestion() {
    String actualQuestion = question.getQuestion();
    assertEquals("DUCK", actualQuestion);
  }

  /**
   * Tests for setDifficulty
   */
  @Test
  void setDifficulty() {
    question.setDifficulty(Difficulty.HARD);
    Difficulty actualDifficulty = question.getDifficulty();
    assertEquals(Difficulty.HARD, actualDifficulty);
  }
}