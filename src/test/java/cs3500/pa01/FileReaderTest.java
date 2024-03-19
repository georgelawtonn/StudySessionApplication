package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.FileInfo;
import cs3500.pa01.FileReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing FileReader
 */
class FileReaderTest {

  Path dir;
  Path simplePath;
  File simpleFile;
  FileInfo fileInfoOne;
  BasicFileAttributes fatt;
  FileReader fr;

  /**
   * Initializes data necessary for testing FileReader
   *
   * @throws IOException if readFails
   */
  @BeforeEach
  void initFileInfo() throws IOException {
    dir = Path.of("dir");
    simplePath = Path.of("TestDir/a.md");
    simplePath = simplePath.toAbsolutePath();
    simpleFile = simplePath.toFile();
    fatt = Files.readAttributes(simplePath, BasicFileAttributes.class);
    fileInfoOne = new FileInfo(simpleFile, fatt);
    fr = new FileReader();
  }

  /**
   * Tests for preVisitDirectory in the FileReader class
   *
   * @throws IOException if preVisitDirectory fails
   */
  @Test
  void preVisitDirectory() throws IOException {
    assertEquals(fr.preVisitDirectory(dir, fatt), FileVisitResult.CONTINUE);
  }

  /**
   * Tests for visitFile in the FileReader class
   *
   * @throws IOException if visitFile fails
   */
  @Test
  void visitFile() throws IOException {
    assertEquals(fr.visitFile(dir, fatt), FileVisitResult.CONTINUE);
    assertEquals(fr.visitFile(simplePath, fatt), FileVisitResult.CONTINUE);
  }

  /**
   * Tester method for visitFileFailed in FileReader Class
   *
   * @throws IOException if visitFileFailed fails
   */
  @Test
  void visitFileFailed() throws IOException {
    assertEquals(fr.visitFileFailed(simplePath, new IOException()), FileVisitResult.CONTINUE);
  }

  /**
   * Tester method for postVisitDirectory in FileReader Class
   *
   * @throws IOException if postVisitDirectory fails
   */
  @Test
  void postVisitDirectory() throws IOException {
    assertEquals(fr.postVisitDirectory(dir, new IOException()), FileVisitResult.CONTINUE);
  }

  /**
   * Tester method for getMdFiles in FileReaderClass
   *
   * @throws IOException if visitFile fails
   */
  @Test
  void getMdFiles() throws IOException {
    ArrayList<FileInfo> fi = new ArrayList<>();
    assertEquals(fr.getMdFiles(), fi);
    fr.visitFile(simplePath, fatt);
    assertEquals(fr.getMdFiles().get(0).getFileAttcd(), fileInfoOne.getFileAttcd());
    assertEquals(fr.getMdFiles().get(0).getFileAttlma(), fileInfoOne.getFileAttlma());
    assertEquals(fr.getMdFiles().get(0).getFileLoc(), fileInfoOne.getFileLoc());
  }


}