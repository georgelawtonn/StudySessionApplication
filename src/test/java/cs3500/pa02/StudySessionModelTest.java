package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the StudySessionModel
 */
class StudySessionModelTest {

  private StudySessionModel studySessionModel;
  private StringBuilder output;
  private Readable input;
  StudySessionController ssc;
  Path dir;
  Path tempDir;
  Path tempFile;
  Path tempFileTwo;
  SrFileReader srfr;
  Question expectedQuestion;
  Question expectedQuestionTwo;
  Question basicQuestion;
  StringBuilder outcome;

  /**
   * Initialization of test data
   */
  @BeforeEach
  void initSrFileReaderInfo() throws IOException {
    input = new StringReader("");
    output = new StringBuilder();
    studySessionModel = new StudySessionModel(input, output);
    ssc = new StudySessionController(input, output);
    dir = Path.of("TestDir/");
    dir = dir.toAbsolutePath();
    tempDir = Files.createTempDirectory(dir, "testing");
    tempFile = Files.createTempFile(tempDir, "ExampleAGEORGEL", ".sr");
    tempFileTwo = Files.createTempFile(tempDir, "ExampleBGeorgeL", ".sr");
    try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
      writer.write("[[GOOSE?:::GOOSE!~HARD]]" + System.lineSeparator());
    }
    try (BufferedWriter writer = Files.newBufferedWriter(tempFileTwo, StandardCharsets.UTF_8)) {
      writer.write("[[GOOSE?:::GOOSE!~HARD]]" + System.lineSeparator()
          + "[[GOOSE?:::GOOSE!~EASY]]");
    }
    srfr = new SrFileReader();
    expectedQuestion = new Question("GOOSE?", "GOOSE!", Difficulty.HARD);
    expectedQuestionTwo = new Question("GOOSE?", "GOOSE!", Difficulty.EASY);
    basicQuestion = new Question("Guess", "Who", Difficulty.HARD);

    outcome = new StringBuilder();
    outcome.append("Number of Questions Answered: 0");
    outcome.append(System.lineSeparator());
    outcome.append("Number of Questions Changed From Easy To Hard: 0");
    outcome.append(System.lineSeparator());
    outcome.append("Number of Questions Changed From Hard To Easy: 0");
    outcome.append(System.lineSeparator());
    outcome.append("Updated Total Hard: 1");
    outcome.append(System.lineSeparator());
    outcome.append("Updated Total Easy: 1");
    outcome.append(System.lineSeparator());
  }

  /**
   * Deletes temp files after tests
   *
   * @throws IOException if delete fails
   */
  @AfterEach
  void deleteTemp() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempFileTwo);
    Files.delete(tempDir);
  }

  /**
   * Tests for retrieveQuestion (singular)
   */
  @Test
  void retrieveQuestionsSingular() {
    studySessionModel.retrieveQuestions(tempFile, 1, ssc);
    Question actualQuestion = studySessionModel.retrieveNextQuestion();
    assertEquals(actualQuestion.getQuestion(), expectedQuestion.getQuestion());
    assertEquals(actualQuestion.getAnswer(), expectedQuestion.getAnswer());
    assertEquals(actualQuestion.getDifficulty(), expectedQuestion.getDifficulty());
  }

  /**
   * Tests for retrieveQuestion (multiple)
   */
  @Test
  void retrieveQuestionsMultiple() {
    studySessionModel.retrieveQuestions(tempFileTwo, 2, ssc);
    Question actualQuestion = studySessionModel.retrieveNextQuestion();
    assertEquals(actualQuestion.getQuestion(), expectedQuestion.getQuestion());
    assertEquals(actualQuestion.getAnswer(), expectedQuestion.getAnswer());
    assertEquals(actualQuestion.getDifficulty(), expectedQuestion.getDifficulty());
    actualQuestion = studySessionModel.retrieveNextQuestion();
    assertEquals(actualQuestion.getQuestion(), expectedQuestionTwo.getQuestion());
    assertEquals(actualQuestion.getAnswer(), expectedQuestionTwo.getAnswer());
    assertEquals(actualQuestion.getDifficulty(), expectedQuestionTwo.getDifficulty());
  }

  /**
   * Tests for errors thrown
   */
  @Test
  void retrieveQuestionsError() {
    Path path = Path.of("");
    assertThrows(StackOverflowError.class, () -> {
      studySessionModel.retrieveQuestions(path, 1, ssc);
    });
    assertThrows(StackOverflowError.class, () -> {
      studySessionModel.retrieveQuestions(tempFile, 2, ssc);
    });
  }

  /**
   * Tests for updateQuestionStatus
   */
  @Test
  void updateQuestionStatus() {
    studySessionModel.updateQuestionStatus(expectedQuestion, Difficulty.EASY);
    studySessionModel.updateQuestionStatus(expectedQuestionTwo, Difficulty.HARD);
    studySessionModel.updateQuestionStatus(basicQuestion, Difficulty.HARD);
    assertEquals(expectedQuestion.getDifficulty(), Difficulty.EASY);
    assertEquals(expectedQuestionTwo.getDifficulty(), Difficulty.HARD);
    assertEquals(basicQuestion.getDifficulty(), Difficulty.HARD);
  }

  /**
   * Tests for retrieveStats
   */
  @Test
  void retrieveStats() {
    studySessionModel.retrieveQuestions(tempFileTwo, 2, ssc);
    assertEquals(studySessionModel.retrieveStats().toString(), outcome.toString());
  }

  /**
   * Tests for updateSrFile
   *
   * @throws IOException if readAllLines fails
   */
  @Test
  void updateSrFile() throws IOException {
    studySessionModel.retrieveQuestions(tempFileTwo, 2, ssc);
    Question q = studySessionModel.retrieveNextQuestion();
    studySessionModel.updateQuestionStatus(q, Difficulty.EASY);
    studySessionModel.updateSrFile(tempFileTwo);
    assertEquals(Files.readAllLines(tempFileTwo).toString(), "[[[GOOSE?:::GOOSE!~EASY]], "
        + "[[GOOSE?:::GOOSE!~EASY]]]");
  }
}