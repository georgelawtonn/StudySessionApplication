package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the SrFileUpdater class
 */
class SrFileUpdaterTest {
  Path dir;
  Path tempDir;
  Path tempFile;
  SrFileUpdater srfu;
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
    srfu = new SrFileUpdater();
    allQ = new ArrayList<>(List.of(new Question("GOOSE?", "GOOSE!", Difficulty.HARD)));
  }

  /**
   * Deletes temp files
   *
   * @throws IOException if delete fails
   */
  @AfterEach
  void deleteTemp() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempDir);
  }

  /**
   * Tests for updateFile
   *
   * @throws IOException if readAllLines fails
   */
  @Test
  void updateFile() throws IOException {
    srfu.updateFile(tempFile, allQ);
    assertEquals(Files.readAllLines(tempFile).get(0), "[[GOOSE?:::GOOSE!~HARD]]");
  }
}