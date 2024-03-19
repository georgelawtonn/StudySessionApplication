package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for formatType enum
 */
class FormatTypeTest {
  FormatType question;
  FormatType answer;
  FormatType difficulty;

  /**
   * Initialization of test data
   */
  @BeforeEach
  void initFormatTypes() {
    question = FormatType.QUESTION;
    answer = FormatType.ANSWER;
    difficulty = FormatType.DIFFICULTY;
  }

  /**
   * Tests the names of the enum
   */
  @Test
  void values() {
    assertEquals("QUESTION", question.name());
    assertEquals("ANSWER", answer.name());
    assertEquals("DIFFICULTY", difficulty.name());
  }

  /**
   * Tests the value of the enum
   */
  @Test
  void valueOf() {
    assertEquals(0, question.ordinal());
    assertEquals(1, answer.ordinal());
    assertEquals(2, difficulty.ordinal());
  }
}