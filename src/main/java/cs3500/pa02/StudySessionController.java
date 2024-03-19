package cs3500.pa02;


import java.nio.file.Path;

/**
 * Controller for a StudySession
 */
public class StudySessionController {

  private final ConsoleReader srcReader;
  private final ConsoleDisplay consoleDisplay;

  /**
   * StudySessionController Constructor
   *
   * @param input The input location/Readable
   * @param output The output location/Appendable
   */
  public StudySessionController(Readable input, Appendable output) {
    srcReader = new ConsoleReader(input);
    consoleDisplay = new ConsoleDisplay(output);
  }

  /**
   * Starts the studySession and displays/retrieves initial sourcePrompt and questionCount
   *
   * @param input Readable
   * @param output Appendable
   */
  public void initializeApp(Readable input, Appendable output) {
    consoleDisplay.displaySourcePrompt();
    String srpath = srcReader.read();
    consoleDisplay.displayQuestionCountPrompt();
    int questionCount = 0;
    try {
      questionCount = Integer.parseInt(srcReader.read());
    } catch (NumberFormatException nfe) {
      consoleDisplay.displayInputError("Invalid Number");
      this.initializeApp(input, output);
    }
    StudySessionModel studySessionModel = new StudySessionModel(input, output);
    initializeQuestionPhase(Path.of(srpath), questionCount, studySessionModel);
    studySessionModel.updateSrFile(Path.of(srpath));
  }

  /**
   * Initializes the questionPhase of the studySession
   *
   * @param srpath The path of the .sr file
   * @param questionCount The number of questions to study
   * @param studySessionModel The current studySessionModel object
   */
  private void initializeQuestionPhase(Path srpath, int questionCount,
                                       StudySessionModel studySessionModel) {
    studySessionModel.retrieveQuestions(srpath, questionCount, this);
    for (int i = 0; i < questionCount; i++) {
      Question currentQuestion = studySessionModel.retrieveNextQuestion();
      String question = currentQuestion.getQuestion();
      consoleDisplay.displayQuestion(question);
      String choice = optionPhase(currentQuestion, studySessionModel);
      if (choice.equals("1")) {
        break;
      }
    }
    StringBuilder stats = studySessionModel.retrieveStats();
    consoleDisplay.displayStats(stats);
  }

  /**
   * The optionPhase of the studySession
   *
   * @param currentQuestion The current question
   * @param studySessionModel The current studySessionModel
   * @return The chosen option
   */
  private String optionPhase(Question currentQuestion, StudySessionModel studySessionModel) {
    String option = srcReader.read();
    switch (option) {
      case "1":
        break;
      case "2":
        studySessionModel.updateQuestionStatus(currentQuestion, Difficulty.HARD);
        break;
      case "3":
        studySessionModel.updateQuestionStatus(currentQuestion, Difficulty.EASY);
        break;
      case "4":
        String answer = currentQuestion.getAnswer();
        consoleDisplay.displayAnswer(answer);
        optionPhase(currentQuestion, studySessionModel);
        break;
      default:
        consoleDisplay.displayInputError("Option Input Is Not Valid, "
            + "Pick Valid Option");
        optionPhase(currentQuestion, studySessionModel);
        break;
    }
    return option;
  }

  /**
   * Calls displayInputError and displays invalidMessage
   *
   * @param invalidMessage The message to be displayed
   */
  public void invalidInput(String invalidMessage) {
    consoleDisplay.displayInputError(invalidMessage);
  }

}
