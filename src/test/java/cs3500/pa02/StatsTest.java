package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for stats
 */
class StatsTest {

  Stats stats;
  StringBuilder outcome;

  /**
   * Initialization of test data
   */
  @BeforeEach
  void initInfo() {
    stats = new Stats();
    outcome = new StringBuilder();
    outcome.append("Number of Questions Answered: 1");
    outcome.append(System.lineSeparator());
    outcome.append("Number of Questions Changed From Easy To Hard: 1");
    outcome.append(System.lineSeparator());
    outcome.append("Number of Questions Changed From Hard To Easy: 1");
    outcome.append(System.lineSeparator());
    outcome.append("Updated Total Hard: 10");
    outcome.append(System.lineSeparator());
    outcome.append("Updated Total Easy: 5");
    outcome.append(System.lineSeparator());
  }

  /**
   * Tests for getStats, and what affects it
   */
  @Test
  void getStats() {
    stats.incrementEasyToHard();
    stats.incrementQuestionsAnswered();
    stats.incrementHardToEasy();
    assertEquals(stats.getStats(5, 10).toString(), outcome.toString());
  }
}