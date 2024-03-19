package cs3500.pa02;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class necessary for formatting in and out of the .sr file format
 */
public class Format {

  /**
   * Formats out of the .sr file format
   *
   * @param text A line within the sr file
   * @return Question class with info from sr file
   */
  public static Question formatDataFromSr(String text) {
    Pattern questionTextP = Pattern.compile("\\[\\[.*?:::");
    Pattern answerTextP = Pattern.compile(":::.*?~");
    Pattern difficultyP = Pattern.compile("~.*?]]");
    String question = findQuestion(text, questionTextP, FormatType.QUESTION).trim();
    String answer = findQuestion(text, answerTextP, FormatType.ANSWER).trim();
    String tempDifficulty = findQuestion(text, difficultyP, FormatType.DIFFICULTY).trim();
    Difficulty difficulty;
    if (tempDifficulty.equals("HARD")) {
      difficulty = Difficulty.HARD;
    } else if (tempDifficulty.equals("EASY")) {
      difficulty = Difficulty.EASY;
    } else {
      throw new IllegalArgumentException(".sr file has invalid difficulty setting");
    }
    return new Question(question, answer, difficulty);
  }

  /**
   * Uses patterns to group each part of the question into certain parts
   *
   * @param text A line from the srFile
   * @param findPattern A pattern to find specific parts of the question
   * @param formatType The formatType to return as
   * @return A formatted question, answer, or difficulty
   */
  private static String findQuestion(String text, Pattern findPattern, FormatType formatType) {
    Matcher patternMatcher = findPattern.matcher(text);
    if (patternMatcher.find()) {
      return removeSrFormat(patternMatcher.group(), formatType);
    } else {
      throw new RuntimeException("Invalid .sr file");
    }
  }

  /**
   * Removes sr Format given a piece of grouped text, and the part of the question
   *
   * @param text A piece of grouped text
   * @param formatType A FormatType that specifies the part of the question
   * @return A formatted piece of the question
   */
  private static String removeSrFormat(String text, FormatType formatType) {
    if (formatType == FormatType.QUESTION) {
      // Removes [[ and :::
      return text.substring(2, text.length() - 3);
    } else if (formatType == formatType.ANSWER) {
      // Removes ::: and ~
      return text.substring(3, text.length() - 1);
    } else {
      // Removes ~ and ]]
      return text.substring(1, text.length() - 2);
    }
  }

  /**
   * Formats data back into the sr file style
   *
   * @param questions All questions unformatted from the sr file
   * @param allQuestions A clean StringBuilder
   */
  public static void formatData(ArrayList<Question> questions, StringBuilder allQuestions) {
    Format.orderByDifficulty(questions);
    String formattedText;
    for (Question question : questions) {
      formattedText = ("[[" + question.getQuestion() + ":::" + question.getAnswer() + "~"
          + question.getDifficulty() + "]]");
      allQuestions.append(formattedText);
      allQuestions.append(System.lineSeparator());
    }
  }

  /**
   * Orders the questions based on difficulty
   *
   * @param questions All questions
   */
  private static void orderByDifficulty(ArrayList<Question> questions) {
    questions.sort((a, b) -> a.getDifficulty().compareTo(b.getDifficulty()));
  }
}
