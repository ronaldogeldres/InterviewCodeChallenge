package edu.rjgc.interviewcodechallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class Response {

    @JsonProperty("total_chars")
    private long totalChars;

    @JsonProperty("total_numeric_chars")
    private long totalNumericChars;

    @JsonProperty("total_alpha_chars")
    private long totalAlphaChars;

    @JsonProperty("total_uppercase_alpha_charts")
    private long totalUppercaseAlphaCharts;

    @JsonProperty("total_lowercase_alpha_chars")
    private long totalLowercaseAlphaChars;

    @JsonProperty("total_white_space")
    private long totalWhiteSpace;

    @JsonProperty("total_punctuation_space")
    private long totalPunctuationSpace;

    @JsonProperty("total_words")
    private long totalWords;

    @JsonProperty("top_10")
    private Map<String, Node> top10;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Node {
        private long count;
        private double percentage;
    }
}
