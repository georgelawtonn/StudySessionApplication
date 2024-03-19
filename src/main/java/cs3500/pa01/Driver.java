package cs3500.pa01;

import cs3500.pa02.StudySessionController;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This is the main driver of this project.
 * Calls upon other methods in order to achieve the final objective of
 * placing a compiled notes file from given paths and ordering.
 */
public class Driver {
  /**
   * Retrieves argument information and calls upon methods in order
   * to produce a compiled file.
   *
   * @param args User inputs
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      Readable input = new InputStreamReader(System.in);
      Appendable output = new PrintStream(System.out);
      StudySessionController sessionController = new StudySessionController(input, output);
      sessionController.initializeApp(input, output);
    } else {
      Path path = Path.of(args[0]);
      String order = args[1];
      Path outputPath = Path.of(args[2]);
      if (!path.isAbsolute()) {
        path = path.toAbsolutePath();
      }
      if (!outputPath.isAbsolute()) {
        outputPath = outputPath.toAbsolutePath();
      }
      ArrayList<File> content = FileProcessor.simpleRetrieval(path, order);
      MyFileWriter fw = new MyFileWriter();
      fw.generateFile(content, outputPath);
    }
  }
}