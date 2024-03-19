package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.FileInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing FileInfo
 */
class FileInfoTest {

  Path simplePath;
  File simpleFile;
  FileInfo fileInfoOne;
  BasicFileAttributes fatt;
  Path dir;
  Path tempDir;
  Path tempFile;
  Path tempFileTwo;
  Path tempFileThree;
  FileInfo tempfileInfoOne;
  FileInfo tempfileInfoTwo;
  FileInfo tempfileInfoThree;
  FileTime f3c;
  FileTime f1c;
  FileTime f2c;

  /**
   * Initializes the data necessary to test the FileInfo class
   *
   * @throws IOException May be caused by tempDir creation
   */
  @BeforeEach
  void initFileInfo() throws IOException {
    dir = Path.of("TestDir/");
    dir = dir.toAbsolutePath();
    tempDir = Files.createTempDirectory(dir, "testing");
    simplePath = Path.of("TestDir/a.md");
    simplePath = simplePath.toAbsolutePath();
    simpleFile = simplePath.toFile();
    fatt = Files.readAttributes(simplePath, BasicFileAttributes.class);
    fileInfoOne = new FileInfo(simpleFile, fatt);
    tempFile = Files.createTempFile(tempDir, "ExampleAGEORGEL", ".md");
    tempFileTwo = Files.createTempFile(tempDir, "ExampleBGEORGEL", ".md");
    tempFileThree = Files.createTempFile(tempDir, "ExampleCGEORGEL", ".md");
    tempfileInfoOne = new FileInfo(tempFile.toFile(), fatt);
    tempfileInfoTwo = new FileInfo(tempFileTwo.toFile(), fatt);
    tempfileInfoThree = new FileInfo(tempFileThree.toFile(), fatt);
    f3c = FileTime.from(Instant.parse("2023-05-15T08:00:00Z"));
    f1c = FileTime.from(Instant.parse("2023-05-17T08:00:00Z"));
    f2c = FileTime.from(Instant.parse("2023-05-18T08:00:00Z"));
  }

  /**
   * Deletes TempFiles
   *
   * @throws IOException If delete fails
   */
  @AfterEach
  void deleteDir() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempFileTwo);
    Files.delete(tempFileThree);
    Files.delete(tempDir);
  }

  /**
   * Tests getFileLoc within the FileInfo class
   */
  @Test
  void getFileLoc() {
    assertEquals(fileInfoOne.getFileLoc(), simpleFile);
  }

  /**
   * Tests getFileAttCD within the FileInfo class
   */
  @Test
  void getFileAttcd() {
    assertEquals(fileInfoOne.getFileAttcd(), fatt.creationTime());
    assertEquals(tempfileInfoOne.getFileAttcd(), f1c);
    assertEquals(tempfileInfoTwo.getFileAttcd(), f2c);
    assertEquals(tempfileInfoThree.getFileAttcd(), f3c);
  }

  /**
   * Tests getFileAttLMA within the FileInfo class
   */
  @Test
  void getFileAttlma() {
    assertEquals(fileInfoOne.getFileAttlma(), fatt.lastModifiedTime());
    assertEquals(tempfileInfoOne.getFileAttlma(), f2c);
    assertEquals(tempfileInfoTwo.getFileAttlma(), f3c);
    assertEquals(tempfileInfoThree.getFileAttlma(), f1c);
  }
}