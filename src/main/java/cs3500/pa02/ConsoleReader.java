package cs3500.pa02;

import java.util.Scanner;

/**
 * The console reader
 */
public class ConsoleReader {
  private final Scanner scanner;

  /**
   * Sets scanner type
   *
   * @param readable Readable location
   */
  public ConsoleReader(Readable readable) {
    scanner = new Scanner(readable);
  }

  /**
   * Reads the user input
   *
   * @return The inputted user input
   */
  public String read() {
    StringBuilder output = new StringBuilder();
    if (scanner.hasNextLine()) {
      output.append(scanner.nextLine());
    }
    return output.toString();
  }
}
