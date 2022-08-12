package edu.rjgc.interviewcodechallenge.util;

import java.util.stream.Stream;

import static edu.rjgc.interviewcodechallenge.util.Constants.ALPHABET;
import static edu.rjgc.interviewcodechallenge.util.Constants.EMPTY_STRING;

public final class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class can't be instantiated");
    }

    public static boolean isLetter(String character) {
        return Stream.of(ALPHABET.split(""))
                .anyMatch(alpha -> alpha.equalsIgnoreCase(character));
    }

    public static boolean isUpperCase(String character) {
        return Stream.of(ALPHABET.split(""))
                .map(String::toUpperCase)
                .anyMatch(alpha -> alpha.equals(character));
    }

    public static boolean isLowerCase(String character) {
        return Stream.of(ALPHABET.split(""))
                .map(String::toLowerCase)
                .anyMatch(alpha -> alpha.equals(character));
    }

    public static boolean isWriteSpace(String character) {
        return character.matches("\t|\n|\\s+");
    }

    public static boolean isPunctuationMark(String character) {
        return character.matches(",|;|:|-");
    }

    public static boolean isDigit(String character) {
        return character.matches("[0-9]");
    }

    public static String getTextWithoutNumbers(String character) {
        return character.replaceAll("[0-9]", EMPTY_STRING);
    }

    public static String getTextWithoutPunctuationMarks(String character) {
        return character.replaceAll(",|;|:|-", EMPTY_STRING);
    }


}
