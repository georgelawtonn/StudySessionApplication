package cs3500.pa02;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * The file updater
 */
public class SrFileUpdater {
  StringBuilder allQuestions = new StringBuilder();

  /**
   * Updates the srFile with changed difficulty
   *
   * @param srLocation The location of the srFile
   * @param questions A list of allQuestions
   */
  public void updateFile(Path srLocation, ArrayList<Question> questions) {
    Format.formatData(questions, allQuestions);
    try {
      FileWriter placer = new FileWriter(srLocation.toFile());
      placer.write(allQuestions.toString().trim());
      placer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
