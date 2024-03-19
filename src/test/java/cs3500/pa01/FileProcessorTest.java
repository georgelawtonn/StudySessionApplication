package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.FileProcessor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the FileProcessor class
 */
class FileProcessorTest {

  Path dir;
  Path tempDir;
  Path tempFile;
  Path tempFileTwo;
  Path tempFileThree;

  /**
   * Initializes the data necessary for FileProcessor Testing
   *
   * @throws IOException If tem
   */
  @BeforeEach
  void initInfo() throws IOException {
    dir = Path.of("TestDir/");
    dir = dir.toAbsolutePath();
    tempDir = Files.createTempDirectory(dir, "testing");
    tempFile = Files.createTempFile(tempDir, "ExampleAGEORGEL", ".md");
    tempFileTwo = Files.createTempFile(tempDir, "ExampleBGEORGEL", ".md");
    tempFileThree = Files.createTempFile(tempDir, "ExampleCGEORGEL", ".md");
  }

  /**
   * Deletes TempFiles
   *
   * @throws IOException if delete fails
   */
  @AfterEach
  void deleteDir() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempFileTwo);
    Files.delete(tempFileThree);
    Files.delete(tempDir);
  }

  /**
   * Tests for simpleRetrieval (filename) ordering
   */
  @Test
  void simpleRetrievalAlpha() {
    assertEquals(FileProcessor.simpleRetrieval(tempDir, "filename"),
        new ArrayList<File>(Arrays.asList(tempFile.toFile(), tempFileTwo.toFile(),
            tempFileThree.toFile())));
  }

  /**
   * Tests for simpleRetrieval (created) ordering
   */
  @Test
  void simpleRetrievalCreated() {
    assertEquals(FileProcessor.simpleRetrieval(tempDir, "created"),
        new ArrayList<File>(Arrays.asList(tempFileThree.toFile(), tempFile.toFile(),
            tempFileTwo.toFile())));
  }

  /**
   * Tests for simpleRetrieval (modified) ordering
   */
  @Test
  void simpleRetrievalModified() {
    assertEquals(FileProcessor.simpleRetrieval(tempDir, "modified"),
        new ArrayList<File>(Arrays.asList(tempFileTwo.toFile(), tempFileThree.toFile(),
            tempFile.toFile())));
  }

  /**
   * Tests for simpleRetrieval errors
   */
  @Test
  void simpleRetrievalError() {
    assertThrows(
        IllegalArgumentException.class,
        () -> FileProcessor.simpleRetrieval(dir, "none"));
    assertThrows(
        RuntimeException.class,
        () -> FileProcessor.simpleRetrieval(Path.of("do"), "none"));

  }
}