package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the myFileWriter class
 */
class MyFileWriterTest {

  File simpleFile;
  File simpleFileTwo;
  Path simplePath;
  Path simplePathTwo;
  File f1;
  File f2;
  File f3;
  File f4;
  MyFileWriter fw;
  ArrayList<File> af;
  Path simplePathSr;

  Path tempDir;
  Path tempFile;
  ArrayList<File> srTestAr;
  Path tempFileTwo;

  /**
   * Initializes data for testing MyFileWriter
   */
  @BeforeEach
  void initFileInfo() throws IOException {
    simplePath = Path.of("outputTestCases/fileWriterTestCase/StudyGuideOrigin.md");
    simplePath = simplePath.toAbsolutePath();
    simpleFile = simplePath.toFile();
    simplePathTwo = Path.of("outputTestCases/fileWriterTestCase/StudyGuide.md");
    simplePathTwo = simplePathTwo.toAbsolutePath();
    simpleFileTwo = simplePathTwo.toFile();
    fw = new MyFileWriter();
    f1 = Path.of("TestDir/a.md").toAbsolutePath().toFile();
    f2 = Path.of("TestDir/z.md").toAbsolutePath().toFile();
    f3 = Path.of("TestDir/something.md").toAbsolutePath().toFile();
    f4 = Path.of("TestDir/somethingelse.md").toAbsolutePath().toFile();
    af = new ArrayList<>(Arrays.asList(f3, f4, f2, f1));
    simplePathSr = Path.of("outputTestCases/fileWriterTestCase/StudyGuide.sr").toAbsolutePath();

    Path rdir = Path.of("TestDir/");
    rdir = rdir.toAbsolutePath();
    tempDir = Files.createTempDirectory(rdir, "testing");
    tempFile = Files.createTempFile(tempDir, "ExampleAGEORGEL", ".md");
    try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
      writer.write("[[GOOSE?:::GOOSE!]]" + System.lineSeparator());
    }
    srTestAr = new ArrayList<>(List.of(tempFile.toFile()));
    tempFileTwo = Files.createTempFile(tempDir, "ExampleAGEORGEL", ".sr");
  }

  /**
   * Deletes tempFiles after use
   *
   * @throws IOException If deletes fail
   */
  @AfterEach
  void deleteTestFiles() throws IOException {
    Files.delete(simplePathTwo);
    Files.delete(simplePathSr);
    Files.delete(tempFile);
    Files.delete(tempFileTwo);
    Files.delete(tempDir);
  }

  /**
   * Tests for the generateFile class (and all helpers)
   *
   * @throws IOException if generateFile fails or delete fails
   */
  @Test
  void fileComparison() throws IOException {
    fw.generateFile(af, simplePathTwo);
    assertEquals(Files.readAllLines(simplePath), Files.readAllLines(simplePathTwo));
    fw.generateFile(srTestAr, tempFileTwo);
    assertEquals(Files.readAllLines(tempFileTwo).toString(), "[[[GOOSE?:::GOOSE! ~ HARD]]]");
  }
}