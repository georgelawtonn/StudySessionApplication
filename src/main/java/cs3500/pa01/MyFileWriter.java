package cs3500.pa01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The MyFileWriter class formats, produces and places the final file into the correct location
 */
public class MyFileWriter {

  MyFileWriter() {
  }

  private StringBuilder completeContents = new StringBuilder();
  private final StringBuilder srContents = new StringBuilder();

  /**
   * generateFile takes in an ArrayList of files scans their contents, formats them,
   * and then places the final file in the outputPath location
   *
   * @param content ArrayList of ordered Files
   * @param outputPath Final output location
   */
  public void generateFile(ArrayList<File> content, Path outputPath) {
    for (File file : content) {
      this.scanContent(file);
    }
    this.cleanContent();
    try {
      FileWriter placer = new FileWriter(outputPath.toFile());
      placer.write(completeContents.toString().trim());
      placer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    placeSrContent(outputPath);
  }

  /**
   * cleanContent formats the content such that it follows the guidelines, and then
   * sets completeContents to the formatted version
   */
  private void cleanContent() {
    // Explanation of Regex (Because it took long time to figure out, and for future reminder)
    // Ordering of regex is basically from left to right
    // "\\" is need to make brackets literal
    // "(?s)" DOTALL makes . equivalent to any char
    // ".*" means that any character to any count can be between the [[]] and
    // "?" greedy quantifier, makes it, so it gets the smallest version of the pattern
    // "|" or
    // "#" is for header
    // [^\r\n]* means that any character besides line breaks can be here a multitude of times
    Pattern pattern = Pattern.compile("\\[\\[(?s).*?\\]\\]|#[^\r\n]*");
    Matcher filterer = pattern.matcher(completeContents.toString());
    StringBuilder output = new StringBuilder();
    while (filterer.find()) {
      String text = filterer.group();
      // This pattern just removes line breaks and additional spaces from the multiline importants
      Pattern patternTwo = Pattern.compile("[\r\n]\\s*");
      Matcher removeLineBreaks = patternTwo.matcher(text);
      StringBuilder trueText = new StringBuilder();
      while (removeLineBreaks.find()) {
        removeLineBreaks.appendReplacement(trueText, " ");
      }
      removeLineBreaks.appendTail(trueText);
      text = trueText.toString();
      if (text.contains(":::")) {
        addToSrContent(text);
      } else {
        if (text.startsWith("#")) {
          output.append(System.lineSeparator());
        } else {
          text = "- " + text.substring(2, text.length() - 2);
        }
        output.append(text);
        output.append(System.lineSeparator());
      }
    }
    completeContents = output;
  }

  /**
   * Adds to the srContents field with formatting
   *
   * @param content The string to be added
   */
  private void addToSrContent(String content) {
    content = content.replace("]]", " ~ HARD]]");
    srContents.append(content);
    srContents.append(System.lineSeparator());
  }

  /**
   * Places srContent field into the sr file
   *
   * @param outputPath The srFile path
   */
  private void placeSrContent(Path outputPath) {
    try {
      String stringOutputPath = outputPath.toString();
      stringOutputPath = stringOutputPath.substring(0, stringOutputPath.length() - 3);
      File srOutput = new File(stringOutputPath + ".sr");
      FileWriter placer = new FileWriter(srOutput);
      placer.write(srContents.toString().trim());
      placer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * ScanContent takes in a File and then appends the inner contents of said file into
   * completeContents
   *
   * @param file Passed through file
   */
  private void scanContent(File file) {
    Path filePath = file.toPath();
    Scanner fileScanner = null;
    try {
      fileScanner = new Scanner(filePath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    while (fileScanner.hasNextLine()) {
      String text = fileScanner.nextLine().trim();
      completeContents.append(text);
      completeContents.append(System.lineSeparator());
    }
  }

}




