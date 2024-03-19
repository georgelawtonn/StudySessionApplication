package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Difficulty Enum
 */
class DifficultyTest {
  Difficulty hard;
  Difficulty easy;

  /**
   * Initialization of test data
   */
  @BeforeEach
  void initDifficulty() {
    hard = Difficulty.HARD;
    easy = Difficulty.EASY;
  }

  /**
   * Tests the names of the enum
   */
  @Test
  void values() {
    assertEquals("HARD", hard.name());
    assertEquals("EASY", easy.name());
  }

  /**
   * Tests the value of the enum
   */
  @Test
  void valueOf() {
    assertEquals(0, hard.ordinal());
    assertEquals(1, easy.ordinal());
  }
}