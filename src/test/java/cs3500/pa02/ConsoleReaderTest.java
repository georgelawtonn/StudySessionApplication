package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * Tests for ConsoleReader
 */
class ConsoleReaderTest {

  /**
   * Tests for the ConsoleReader read
   */
  @Test
  void read() {
    String input = "Hello, world!";
    String inputTwo = "";
    ConsoleReader consoleReader = new ConsoleReader(new StringReader(input));
    ConsoleReader consoleReaderTwo = new ConsoleReader(new StringReader(inputTwo));
    String result = consoleReader.read();
    String resultTwo = consoleReaderTwo.read();
    assertEquals(input, result);
    assertEquals(inputTwo, resultTwo);
  }
}