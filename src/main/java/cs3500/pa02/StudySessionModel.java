package cs3500.pa02;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A StudySessionModel handles the StudySession retrievals of stats, and questions
 */
public class StudySessionModel {
  private ArrayList<Question> allQuestions = new ArrayList<>();
  private final ArrayList<Question> currentQuestionSubset = new ArrayList<>();
  private int hardQuestions = 0;
  private int currentQuestion = 0;
  private final Stats stats = new Stats();
  private final Readable input;
  private final Appendable output;

  /**
   * The StudySessionModel constructor
   *
   * @param input The input location/Readable
   * @param output The output location/Appendable
   */
  public StudySessionModel(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
  }

  /**
   * Retrieves questions necessary for the studySession
   *
   * @param src The file from which to pull questions from
   * @param numberOfQuestions The number of questions to have in the testing set
   * @param sessionController The sessionController (necessary if invalid inputs)
   */
  public void retrieveQuestions(Path src, int numberOfQuestions,
                                StudySessionController sessionController) {
    SrFileReader srReader = new SrFileReader();
    try {
      srReader.readAllQuestions(src);
    } catch (RuntimeException noFile) {
      sessionController.invalidInput("No Valid File Found!");
      sessionController.initializeApp(input, output);
    }
    allQuestions = srReader.getAllQuestions();
    if (allQuestions.size() < numberOfQuestions) {
      sessionController.invalidInput("Not enough questions in Study File");
      sessionController.initializeApp(input, output);
    }
    pickCurrentQuestionSubset(numberOfQuestions);
  }

  /**
   * Counts the number of hardQuestions
   */
  private void countHardQuestions() {
    hardQuestions = 0;
    for (Question question : allQuestions) {
      if (question.getDifficulty() == Difficulty.HARD) {
        hardQuestions++;
      }
    }
  }

  /**
   * Gets a subset of allQuestions
   *
   * @param numberOfQuestions The number of questions that will be in the subset
   */
  private void pickCurrentQuestionSubset(int numberOfQuestions) {
    currentQuestionSubset.clear();
    countHardQuestions();
    List<Question> hardSubset = allQuestions.subList(0, hardQuestions);
    Collections.shuffle(hardSubset);
    if (numberOfQuestions > hardQuestions) {
      currentQuestionSubset.addAll(hardSubset);
      List<Question> easySubset = allQuestions.subList(hardQuestions, allQuestions.size());
      Collections.shuffle(easySubset);
      numberOfQuestions = numberOfQuestions - hardQuestions;
      currentQuestionSubset.addAll(easySubset.subList(0, numberOfQuestions));
    } else {
      currentQuestionSubset.addAll(hardSubset.subList(0, numberOfQuestions));
    }
  }

  /**
   * Retrieves the next question in the question subset
   *
   * @return The next question
   */
  public Question retrieveNextQuestion() {
    Question nextQuestion = currentQuestionSubset.get(currentQuestion);
    currentQuestion++;
    return nextQuestion;
  }

  /**
   * Updates the question difficulty
   *
   * @param question The currentQuestion
   * @param newDifficulty The newDifficulty
   */
  public void updateQuestionStatus(Question question, Difficulty newDifficulty) {
    Difficulty currentDifficulty = question.getDifficulty();
    if (currentDifficulty != newDifficulty) {
      if (currentDifficulty == Difficulty.HARD) {
        stats.incrementHardToEasy();
      } else {
        stats.incrementEasyToHard();
      }
    }
    stats.incrementQuestionsAnswered();
    question.setDifficulty(newDifficulty);
  }

  /**
   * Retrieves stats and puts them into a StringBuilder
   *
   * @return A formatted stats StringBuilder
   */
  public StringBuilder retrieveStats() {
    countHardQuestions();
    int updatedHard = hardQuestions;
    int updatedEasy = allQuestions.size() - updatedHard;
    return stats.getStats(updatedEasy, updatedHard);
  }

  /**
   * Updates the srFile with new information
   *
   * @param srLocation The path of the sr file
   */
  public void updateSrFile(Path srLocation) {
    SrFileUpdater fileUpdater = new SrFileUpdater();
    fileUpdater.updateFile(srLocation, allQuestions);
  }
}
