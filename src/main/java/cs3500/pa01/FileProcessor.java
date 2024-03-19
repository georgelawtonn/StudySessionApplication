package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * The FileProcessor class clean and retrieves data from FileReader
 */
public class FileProcessor {

  /**
   * simpleRetrieval walks fileTree, retrieves the information, sorts, and then cleans any
   * unnecessary information
   *
   * @param path Directory Path
   * @param order Ordering Flag
   * @return ArrayList of File (sorted and cleaned of basic attributes)
   */
  public static ArrayList<File> simpleRetrieval(Path path, String order) {
    FileReader fr = new FileReader();
    ArrayList<FileInfo> content;
    try {
      Files.walkFileTree(path, fr);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    content = fr.getMdFiles();
    FileProcessor.orderFiles(content, order);
    return FileProcessor.stripBasicAtt(content);
  }

  /**
   * Orders the files within content depending on the order flag parameter
   *
   * @param content FileInfo of mdFiles
   * @param order Ordering type
   */
  private static void orderFiles(ArrayList<FileInfo> content, String order) {
    if (order.equalsIgnoreCase("filename")) {
      content.sort(
          (a, b) -> a.getFileLoc().toString().compareToIgnoreCase(b.getFileLoc().toString()));
    } else if (order.equalsIgnoreCase("created")) {
      content.sort(
          (a, b) -> a.getFileAttcd().compareTo(b.getFileAttcd()));
    } else if (order.equalsIgnoreCase("modified")) {
      content.sort(
          (a, b) -> a.getFileAttlma().compareTo(b.getFileAttlma()));
    } else {
      throw new IllegalArgumentException("No proper ordering flag");
    }
  }

  /**
   * Creates an ArrayList of files from the Files within content, disregards BasicAtt info as it is
   * no longer necessary
   *
   * @param content ArrayList of ordered FileInfo
   * @return ArrayList of file (Already Ordered, and now cleaned)
   */
  private static ArrayList<File> stripBasicAtt(ArrayList<FileInfo> content) {
    ArrayList<File> filePaths = new ArrayList<>();
    for (FileInfo fi : content) {
      filePaths.add(fi.getFileLoc());
    }
    return filePaths;
  }

}
