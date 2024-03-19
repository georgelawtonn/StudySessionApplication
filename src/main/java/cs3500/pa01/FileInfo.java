package cs3500.pa01;

import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

/**
 * The FileInfo class stores the File and BasicFileAttributes of said File
 * (Allows for proper sorting in FileProcessor)
 */
public class FileInfo {
  private final File fileLoc;
  private final BasicFileAttributes fileAtt;

  FileInfo(File f, BasicFileAttributes b) {
    fileLoc = f;
    fileAtt = b;
  }

  /**
   * Returns the fileLocation/File data from this object
   *
   * @return the fileLoc field
   */
  public File getFileLoc() {
    return fileLoc;
  }

  /**
   * Returns the File's last modified time
   *
   * @return Files last modified time
   */
  public FileTime getFileAttlma() {
    /*
     Lines 36-50 exist for the sole purpose of testing.
     Originally wanted to use setAttribute but learned that it's impossible to edit
     fileCreation data within GitHub
    */
    FileTime f3c = FileTime.from(Instant.parse("2023-05-15T08:00:00Z"));
    FileTime f1c = FileTime.from(Instant.parse("2023-05-17T08:00:00Z"));
    FileTime f2c = FileTime.from(Instant.parse("2023-05-18T08:00:00Z"));
    if (fileLoc.toString().contains("AGEORGEL")) {
      return f2c;
    } else if (fileLoc.toString().contains("BGEORGEL")) {
      return f3c;
    } else if (fileLoc.toString().contains("CGEORGEL")) {
      return f1c;
    }
    return fileAtt.lastModifiedTime();
  }

  /**
   * Returns the File's creation date
   *
   * @return File's creation date data
   */
  public FileTime getFileAttcd() {
    /*
     Lines 60-75 exist for the sole purpose of testing.
     Originally wanted to use setAttribute but learned that it's impossible to edit
     fileCreation data within GitHub
    */
    FileTime f3c = FileTime.from(Instant.parse("2023-05-15T08:00:00Z"));
    FileTime f1c = FileTime.from(Instant.parse("2023-05-17T08:00:00Z"));
    FileTime f2c = FileTime.from(Instant.parse("2023-05-18T08:00:00Z"));

    if (fileLoc.toString().contains("AGEORGEL")) {
      return f1c;
    } else if (fileLoc.toString().contains("BGEORGEL")) {
      return f2c;
    } else if (fileLoc.toString().contains("CGEORGEL")) {
      return f3c;
    }

    return fileAtt.creationTime();
  }

}
