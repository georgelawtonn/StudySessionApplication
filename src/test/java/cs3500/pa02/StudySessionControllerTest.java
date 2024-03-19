package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * Tests for StudySessionController class
 */
class StudySessionControllerTest {
  Path dir;
  Path tempDir;
  Path tempFile;

  /**
   * Initialization of test data
   */
  @BeforeEach
  void initData() throws IOException {
    dir = Path.of("TestDir/");
    dir = dir.toAbsolutePath();
    tempDir = Files.createTempDirectory(dir, "testing");
    tempFile = Files.createTempFile(tempDir, "ExampleAGEORGEL", ".sr");
    try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
      writer.write("[[GOOSE?:::GOOSE!~HARD]]" + System.lineSeparator()
          + "[[GOOSE?:::GOOSE!~EASY]]");
    }
  }

  /**
   * Deletes temps after tests
   *
   * @throws IOException if delete fails
   */
  @AfterEach
  void deleteTemp() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempDir);
  }

  /**
   * Tests for initializeApp
   */
  @Test
  void initializeApp() {
    Readable input = new StringReader(tempFile.toString() + System.lineSeparator() + "2"
        + System.lineSeparator() + "2" + System.lineSeparator() + "1");
    StringBuilder output = new StringBuilder();
    StudySessionController sessionController = new StudySessionController(input, output);
    sessionController.initializeApp(input, output);
    assertEquals(output.toString().trim(), "Let's get studying!" + System.lineSeparator()
        + "To start please provide a .sr path and enter" + System.lineSeparator()
        + "How many questions would you like to study?" + System.lineSeparator()
        + "Question: GOOSE?" + System.lineSeparator()
        + "Options: (1) Quit , (2) Hard , (3) Easy , (4) Show Answer" + System.lineSeparator()
        + "Question: GOOSE?" + System.lineSeparator()
        + "Options: (1) Quit , (2) Hard , (3) Easy , (4) Show Answer" + System.lineSeparator()
        + "Number of Questions Answered: 1" + System.lineSeparator()
        + "Number of Questions Changed From Easy To Hard: 0" + System.lineSeparator()
        + "Number of Questions Changed From Hard To Easy: 0" + System.lineSeparator()
        + "Updated Total Hard: 1" + System.lineSeparator()
        + "Updated Total Easy: 1");
  }

  /**
   * Tests for initializeApp (ended earlier)
   */
  @Test
  void initializeAppT2() {
    Readable input = new StringReader(tempFile.toString() + System.lineSeparator() + "2"
        + System.lineSeparator() + "3" + System.lineSeparator() + "4"
        + System.lineSeparator() + "1");
    StringBuilder output = new StringBuilder();
    StudySessionController sessionController = new StudySessionController(input, output);
    sessionController.initializeApp(input, output);
    assertEquals(output.toString().trim(), "Let's get studying!" + System.lineSeparator()
        + "To start please provide a .sr path and enter" + System.lineSeparator()
        + "How many questions would you like to study?" + System.lineSeparator()
        + "Question: GOOSE?" + System.lineSeparator()
        + "Options: (1) Quit , (2) Hard , (3) Easy , (4) Show Answer" + System.lineSeparator()
        + "Question: GOOSE?" + System.lineSeparator()
        + "Options: (1) Quit , (2) Hard , (3) Easy , (4) Show Answer" + System.lineSeparator()
        + "Answer: GOOSE!" + System.lineSeparator()
        + "Options: (1) Quit , (2) Hard , (3) Easy, (4) Show Answer Again" + System.lineSeparator()
        + "Number of Questions Answered: 1" + System.lineSeparator()
        + "Number of Questions Changed From Easy To Hard: 0" + System.lineSeparator()
        + "Number of Questions Changed From Hard To Easy: 1" + System.lineSeparator()
        + "Updated Total Hard: 0" + System.lineSeparator()
        + "Updated Total Easy: 2");
  }

  /**
   * Tests for initialize app (other options)
   */
  @Test
  void initializeAppT3() {
    Readable input = new StringReader(tempFile.toString() + System.lineSeparator() + "1"
        + System.lineSeparator() + "5" + System.lineSeparator() + "1");
    StringBuilder output = new StringBuilder();
    StudySessionController sessionController = new StudySessionController(input, output);
    sessionController.initializeApp(input, output);
    assertEquals(output.toString().trim(), "Let's get studying!" + System.lineSeparator()
        + "To start please provide a .sr path and enter" + System.lineSeparator()
        + "How many questions would you like to study?" + System.lineSeparator()
        + "Question: GOOSE?" + System.lineSeparator()
        + "Options: (1) Quit , (2) Hard , (3) Easy , (4) Show Answer" + System.lineSeparator()
        + "Option Input Is Not Valid, Pick Valid Option" + System.lineSeparator()
        + "Number of Questions Answered: 0" + System.lineSeparator()
        + "Number of Questions Changed From Easy To Hard: 0" + System.lineSeparator()
        + "Number of Questions Changed From Hard To Easy: 0" + System.lineSeparator()
        + "Updated Total Hard: 1" + System.lineSeparator()
        + "Updated Total Easy: 1");
  }

}