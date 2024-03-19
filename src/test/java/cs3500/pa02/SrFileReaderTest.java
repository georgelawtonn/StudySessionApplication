package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the SrFileReader class
 */
class SrFileReaderTest {

  Path dir;
  Path tempDir;
  Path tempFile;
  SrFileReader srfr;
  ArrayList<Question> allQ;

  /**
   * Initialization of test data
   */
  @BeforeEach
  void initSrFileReaderInfo() throws IOException {
    dir = Path.of("TestDir/");
    dir = dir.toAbsolutePath();
    tempDir = Files.createTempDirectory(dir, "testing");
    tempFile = Files.createTempFile(tempDir, "ExampleAGEORGEL", ".sr");
    try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
      writer.write("[[GOOSE?:::GOOSE!~HARD]]" + System.lineSeparator());
    }
    srfr = new SrFileReader();
    allQ = new ArrayList<>(List.of(new Question("GOOSE?", "GOOSE!", Difficulty.HARD)));
  }

  /**
   * Deletes temps after testing
   *
   * @throws IOException If delete fails
   */
  @AfterEach
  void deleteTemp() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempDir);
  }

  /**
   * Tests for readAllQuestions
   */
  @Test
  void fileReader() {
    srfr.readAllQuestions(tempFile);
    assertEquals(srfr.getAllQuestions().get(0).getQuestion(), allQ.get(0).getQuestion());
    assertEquals(srfr.getAllQuestions().get(0).getAnswer(), allQ.get(0).getAnswer());
    assertEquals(srfr.getAllQuestions().get(0).getDifficulty(), allQ.get(0).getDifficulty());
  }
}