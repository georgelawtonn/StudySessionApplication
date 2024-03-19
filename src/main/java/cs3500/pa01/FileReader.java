package cs3500.pa01;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * FileReader class reads files of a given directory (implements FileVisitor)
 */
public class FileReader implements FileVisitor<Path> {

  private final ArrayList<FileInfo> mdFiles = new ArrayList<>();

  /**
   * Invoked for a directory before entries in the directory are visited.
   *
   * <p>If this method returns {@link FileVisitResult#CONTINUE CONTINUE},
   * then entries in the directory are visited. If this method returns {@link
   * FileVisitResult#SKIP_SUBTREE SKIP_SUBTREE} or {@link
   * FileVisitResult#SKIP_SIBLINGS SKIP_SIBLINGS} then entries in the
   * directory (and any descendants) will not be visited.
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Invoked for a file in a directory.
   * Also adds to mdFiles in the case that the file is a md file
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    String filename = file.getFileName().toString();
    if (filename.endsWith(".md")) {
      mdFiles.add(new FileInfo(file.toFile(), attrs));
    }
    return FileVisitResult.CONTINUE;
  }

  /**
   * Invoked for a file that could not be visited. This method is invoked
   * if the file's attributes could not be read, the file is a directory
   * that could not be opened, and other reasons.
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Invoked for a directory after entries in the directory, and all of their
   * descendants, have been visited. This method is also invoked when iteration
   * of the directory completes prematurely (by a {@link #visitFile visitFile}
   * method returning {@link FileVisitResult#SKIP_SIBLINGS SKIP_SIBLINGS},
   * or an I/O error when iterating over the directory).
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Returns the mdFiles field from FileReader
   *
   * @return mdFiles field
   */
  public ArrayList<FileInfo> getMdFiles() {
    return mdFiles;
  }
}
