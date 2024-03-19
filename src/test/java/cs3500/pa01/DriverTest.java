package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Driver class
 */
class DriverTest {

  String input;
  String outputA;
  String outputM;
  String outputC;
  String[] mod;
  String[] create;
  String[] alpha;
  Path simplePathA;
  Path simplePathM;
  Path simplePathC;
  Path simplePathAs;
  Path simplePathMs;
  Path simplePathCs;
  Path dir;
  Path tempDir;
  Path tempFile;
  Path tempFileTwo;
  Path tempFileThree;
  Path simplePathAlpha;
  Path simplePathMod;
  Path simplePathCr;
  String empty;
  String[] testEmpty;
  String emptyFile;
  Path emptyPath;
  Path emptyPathSr;

  /**
   * Initializes the data neccessary for properly testing Driver
   *
   * @throws IOException (If buffered writer fails)
   */
  @BeforeEach
  void initInfo() throws IOException {
    dir = Path.of("TestDir/");
    dir = dir.toAbsolutePath();
    tempDir = Files.createTempDirectory(dir, "testing");
    input = tempDir.toString();
    outputA = "outputTestCases/driverTestCaseAlpha/StudyGuide.md";
    outputM = "outputTestCases/driverTestCaseMod/StudyGuide.md";
    outputC = "outputTestCases/driverTestCaseCr/StudyGuide.md";
    simplePathAlpha =
        Path.of("outputTestCases/driverTestCaseAlpha/StudyGuidefn.md").toAbsolutePath();
    simplePathMod = Path.of("outputTestCases/driverTestCaseMod/StudyGuideM.md").toAbsolutePath();
    simplePathCr = Path.of("outputTestCases/driverTestCaseCr/StudyGuideC.md").toAbsolutePath();
    simplePathA = Path.of("outputTestCases/driverTestCaseAlpha/StudyGuide.md").toAbsolutePath();
    simplePathM = Path.of("outputTestCases/driverTestCaseMod/StudyGuide.md").toAbsolutePath();
    simplePathC = Path.of("outputTestCases/driverTestCaseCr/StudyGuide.md").toAbsolutePath();
    simplePathAs = Path.of("outputTestCases/driverTestCaseAlpha/StudyGuide.sr").toAbsolutePath();
    simplePathMs = Path.of("outputTestCases/driverTestCaseMod/StudyGuide.sr").toAbsolutePath();
    simplePathCs = Path.of("outputTestCases/driverTestCaseCr/StudyGuide.sr").toAbsolutePath();
    tempFile = Files.createTempFile(tempDir, "ExampleAGEORGEL", ".md");
    tempFileTwo = Files.createTempFile(tempDir, "ExampleBGEORGEL", ".md");
    tempFileThree = Files.createTempFile(tempDir, "ExampleCGEORGEL", ".md");

    try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
      writer.write("#Hello, world!" + System.lineSeparator());
    }
    try (BufferedWriter writerTwo = Files.newBufferedWriter(tempFileTwo, StandardCharsets.UTF_8)) {
      writerTwo.write("#Hello, world!" + System.lineSeparator() + "[[something]]");
    }
    try (BufferedWriter writerThree = Files.newBufferedWriter(tempFileThree,
        StandardCharsets.UTF_8)) {
      writerThree.write("#Hello, world!" + System.lineSeparator() + "[[not something]]");
    }

    mod = new String[] {input, "modified", outputM};
    create = new String[] {input, "created", outputC};
    alpha = new String[] {input, "filename", outputA};

    empty = "TestDirPA01Empty";
    emptyFile = "TestDirPA01Empty/StudyGuide.md";
    testEmpty = new String[] {empty, "filename", emptyFile};
    emptyPath = Path.of(emptyFile).toAbsolutePath();
    emptyPathSr = Path.of("TestDirPA01Empty/StudyGuide.sr").toAbsolutePath();
  }

  /**
   * Deletes TempFiles
   *
   * @throws IOException (If deletes fail)
   */
  @AfterEach
  void deleteDir() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempFileTwo);
    Files.delete(tempFileThree);
    Files.delete(tempDir);
    Files.delete(simplePathA);
    Files.delete(simplePathM);
    Files.delete(simplePathC);
    Files.delete(simplePathAs);
    Files.delete(simplePathMs);
    Files.delete(simplePathCs);
    Files.delete(emptyPath);
    Files.delete(emptyPathSr);
  }

  /**
   * Tests the main method of the Driver Class
   *
   * @throws IOException (If readAllLines fails)
   */
  @Test
  void main() throws IOException {
    Driver.main(alpha);
    assertEquals(Files.readAllLines(simplePathAlpha), Files.readAllLines(simplePathA));

    Driver.main(create);
    assertEquals(Files.readAllLines(simplePathCr), Files.readAllLines(simplePathC));

    Driver.main(mod);
    assertEquals(Files.readAllLines(simplePathMod), Files.readAllLines(simplePathM));

    // Testing driver line 34
    Driver.main(testEmpty);
    assertEquals(Files.readAllLines(emptyPath).toString(), "[]");
  }
}