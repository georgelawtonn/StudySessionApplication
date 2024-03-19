package cs3500.pa02;

/**
 * Maintains the stats of a StudySession
 */
public class Stats {
  private int easyToHard = 0;
  private int hardToEasy = 0;
  private int questionsAnswered = 0;

  /**
   * Increments the easyToHard field
   */
  public void incrementEasyToHard() {
    easyToHard++;
  }

  /**
   * Increments the hardToEasy field
   */
  public void incrementHardToEasy() {
    hardToEasy++;
  }

  /**
   * Increments the questionsAnswered field
   */
  public void incrementQuestionsAnswered() {
    questionsAnswered++;
  }

  /**
   * Creates a StringBuilder with formatted stats
   *
   * @param updatedEasy The number of questions that are easy in a sr
   * @param updatedHard The number of questions that are hard in a sr
   * @return The formatted stats
   */
  public StringBuilder getStats(int updatedEasy, int updatedHard) {
    StringBuilder statsDisplay = new StringBuilder();
    statsDisplay.append("Number of Questions Answered: " + questionsAnswered
        + System.lineSeparator());
    statsDisplay.append("Number of Questions Changed From Easy To Hard: " + easyToHard
        + System.lineSeparator());
    statsDisplay.append("Number of Questions Changed From Hard To Easy: " + hardToEasy
        + System.lineSeparator());
    statsDisplay.append("Updated Total Hard: " + updatedHard
        + System.lineSeparator());
    statsDisplay.append("Updated Total Easy: " + updatedEasy
        + System.lineSeparator());
    return statsDisplay;
  }
}
