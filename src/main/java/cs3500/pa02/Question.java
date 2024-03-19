package cs3500.pa02;

/**
 * A question class
 */
public class Question {

  String question;
  String answer;
  Difficulty difficulty;

  /**
   * Constructor for the question class
   *
   * @param question The question part of the question
   * @param answer The answer part of the question
   * @param difficulty The difficulty part of the question
   */
  Question(String question, String answer, Difficulty difficulty) {
    this.difficulty = difficulty;
    this.question = question;
    this.answer = answer;
  }

  /**
   * gets the difficulty of this question
   *
   * @return The difficulty
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * gets the answer of this question
   *
   * @return The answer
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * gets the question of this question
   *
   * @return The question
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Sets the difficulty of this question
   *
   * @param difficulty A new difficulty
   */
  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }
}



