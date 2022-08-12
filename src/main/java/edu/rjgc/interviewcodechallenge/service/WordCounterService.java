package edu.rjgc.interviewcodechallenge.service;

import edu.rjgc.interviewcodechallenge.models.Request;
import edu.rjgc.interviewcodechallenge.models.Response;
import edu.rjgc.interviewcodechallenge.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static edu.rjgc.interviewcodechallenge.util.Constants.EMPTY_STRING;

@Service
public class WordCounterService {

    public Response buildReport(final Request request) {

        //TODO: Use bean validation
        final String textToEvaluate = Optional.ofNullable(request)
                .map(Request::getText)
                .filter(Predicate.not(String::isBlank))
                .orElseThrow(() -> new IllegalArgumentException("Text must not be null or empty"));
        final Response.ResponseBuilder responseBuilder = Response.builder();

        /**
         * 1) Cantidad de caracteres totales (incluyendo espacios en blanco) - VALIDATED
         */
        //validated on https://gutool.com/word-count/
        //the results matched - result was: 2664
        final long totalChars = Stream.of(textToEvaluate.split(EMPTY_STRING))
                .count();
        responseBuilder.totalChars(totalChars);

        /**
         * 2) Cantidad de caracteres numéricos (dígitos de 0-9) - VALIDATED
         */
        //validated on https://gutool.com/word-count/
        //added 3 numbers to the postman to force logic
        // the results matched - result was: 3
        final long totalNumericChars = Stream.of(textToEvaluate.split(EMPTY_STRING))
                .filter(StringUtils::isDigit)
                .count();
        responseBuilder.totalNumericChars(totalNumericChars);

        /**
         * 3) Cantidad de letras (totales) - VALIDATED
         */
        //validated on https://gutool.com/word-count/
        //the results matched - result was: 2170
        final long totalAlphaChars = Stream.of(textToEvaluate.split(EMPTY_STRING))
                .filter(StringUtils::isLetter)
                .count();
        responseBuilder.totalAlphaChars(totalAlphaChars);

        /**
         * 4) Cantidad de letras (minúsculas) - VALIDATED
         */
        //validated on https://gutool.com/word-count/
        //the results matched - result was: 2122
        final long totalLowercaseAlphaChars = Stream.of(textToEvaluate.split(EMPTY_STRING))
                .filter(StringUtils::isLowerCase)
                .count();
        responseBuilder.totalLowercaseAlphaChars(totalLowercaseAlphaChars);

        /**
         * 5) Cantidad de letras (mayúsculas) - VALIDATED
         */
        //validated on https://gutool.com/word-count/
        //the results matched - result was: 48
        final long totalUppercaseAlphaCharts = Stream.of(textToEvaluate.split(EMPTY_STRING))
                .filter(StringUtils::isUpperCase)
                .count();
        responseBuilder.totalUppercaseAlphaCharts(totalUppercaseAlphaCharts);

        /**
         * 6) Cantidad de espacios en blanco tipo -> \t \n \\s+ <- VALIDATED
         */
        //validated on Visual Studion using patter \t|\n|\s+
        //the results matched - result was: 400
        final long totalWhiteSpace = Stream.of(textToEvaluate.split(EMPTY_STRING))
                .filter(StringUtils::isWriteSpace)
                .count();
        responseBuilder.totalWhiteSpace(totalWhiteSpace);

        /**
         * 7) Cantidad de caracteres de puntuación ,;:- VALIDATED
         */
        //validated on Visual Studion using patter ,|;|:|-
        //added - : , to force logic to evaluate more characters
        //the results matched - result was: 43
        final long totalPunctuationSpace = Stream.of(textToEvaluate.split(EMPTY_STRING))
                .filter(StringUtils::isPunctuationMark)
                .count();
        responseBuilder.totalPunctuationSpace(totalPunctuationSpace);

        /**
         * 8) Cantidad de palabras - VALIDATED
         */
        //validated on https://gutool.com/word-count/
        //the results matched - result was: 395
        String textWithoutPunctuationMarksAndNumbers = Optional.of(textToEvaluate)
                .map(StringUtils::getTextWithoutNumbers)
                .map(StringUtils::getTextWithoutPunctuationMarks)
                .filter(Predicate.not(String::isBlank))
                .orElseThrow(() -> new IllegalArgumentException("Text without punction marks and numbers is not valid"));
        final long totalWords = Stream.of(textWithoutPunctuationMarksAndNumbers.split("\\s+"))
                .count();
        responseBuilder.totalWords(totalWords);

        /**
         * 9) Top 10 caracteres presentes (ordenados de mayor presencia a menor presencia) con su respectivo conteo
         * de presencia del caracter (y el porcentaje relativo al total de caracteres)
         */
        final Map<String, Long> top10WithoutPercentage = Stream.of(textWithoutPunctuationMarksAndNumbers.split(EMPTY_STRING))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        final Map<String, Response.Node> top10 = top10WithoutPercentage
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(10)
                .collect(Collectors.toMap(stringLongEntry -> stringLongEntry.getKey(), stringLongEntry -> buildNode(stringLongEntry.getValue(), totalChars)));
        responseBuilder.top10(top10);
        return responseBuilder.build();
    }

    private Response.Node buildNode(long value, long totalElements) {
        //Use BigDecimal and use set SCALE to 2
        final double percentage = (value * 100) / totalElements;
        return new Response.Node(value, percentage);
    }
}
