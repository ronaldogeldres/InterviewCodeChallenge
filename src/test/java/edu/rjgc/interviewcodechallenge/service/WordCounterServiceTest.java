package edu.rjgc.interviewcodechallenge.service;

import edu.rjgc.interviewcodechallenge.models.Request;
import edu.rjgc.interviewcodechallenge.models.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterServiceTest {

    private static String TEXT_SAMPLE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla tempus porta ante in dignissim. Donec porta ante vel maximus vehicula. Pellentesque ut porttitor nisi, eu finibus elit. Aenean vitae semper enim. Nunc sagittis lacinia 3 lobortis. Phasellus ornare, augue sed suscipit blandit, erat elit mollis sem, in scelerisque nibh augue et nisl. Sed mollis efficitur tortor, sed finibus risus finibus et. Duis quis diam egestas, dictum turpis ullamcorper, ullamcorper tellus. Fusce vel lorem cursus, laoreet purus et, commodo eros. Morbi vitae metus felis. Pellentesque sagittis vestibulum dolor, non suscipit ipsum aliquet ornare. Sed ac lobortis tortor, malesuada hendrerit dui. Donec id semper dui. Aliquam faucibus nulla eu magna ullamcorper elementum. Suspendisse consectetur vulputate lectus, ultricies 2 interdum nulla blandit ac. Nam eu dolor sit amet metus laoreet porta sed sit amet nibh. Vivamus convallis, eros eu maximus mollis, dolor nulla mattis ligula, nec sollicitudin nisl nibh ac neque. Sed iaculis finibus felis, non pharetra ligula euismod et. Cras orci lorem, laoreet quis sagittis quis, interdum in lectus. Sed cursus vulputate lorem ut mattis. Curabitur dignissim, eros sed laoreet imperdiet, risus ex fermentum lacus, at pellentesque mi tellus id felis. Phasellus a ullamcorper orci, ut aliquet metus. Maecenas blandit lectus non risus pulvinar euismod. Vivamus pulvinar nisi et eros bibendum aliquet. Maecenas at elit condimentum, faucibus nibh nec, malesuada eros. Vestibulum feugiat ullamcorper elit vitae semper. Aliquam non ligula risus. Sed lobortis ante nec elementum congue. Morbi euismod erat vel orci faucibus, ut ullamcorper enim tincidunt. Suspendisse id neque non lectus tincidunt tincidunt eget id velit. Mauris nec commodo elit. Fusce euismod enim eget risus tempus, sit amet finibus mauris facilisis. Phasellus non nibh nec diam rhoncus sodales. Donec tincidunt sagittis dui id sagittis. Donec vitae risus id erat eleifend molestie vitae sit amet ante. In eu tortor porttitor ex egestas venenatis non at augue. Vivamus libero enim, auctor a lacus in, eleifend gravida odio. Vivamus rhoncus semper est, nec rhoncus lorem. In ac ex pharetra, dictum odio sed, faucibus augue. Donec ex tortor, tempor sed elementum sit amet, tincidunt in nisi. Aliquam viverra quis lectus non interdum. Aenean mauris risus, viverra ut ipsum vel, facilisis aliquam dolor. Quisque venenatis libero in nibh aliquet, eget laoreet mauris ornare. Ut eget orci turpis. Integer dignissim varius tortor in laoreet. Pellentesque pretium, neque non laoreet pulvinar, lorem nulla consequat mi, ut tempor tellus lacus quis ex. In at faucibus libero. 1 - : ,";

    private WordCounterService wordCounterService;

    @BeforeEach
    void setUp(){
        wordCounterService = Mockito.spy(new WordCounterService());
    }

    @Test
    void buildReport_valid(){
        //Arrange
        Request request = new Request(TEXT_SAMPLE);
        //Act
        Response response = wordCounterService.buildReport(request);
        //Assert
        assertEquals(2664, response.getTotalChars());
        assertEquals(3, response.getTotalNumericChars());
        assertEquals(2170, response.getTotalAlphaChars());
        assertEquals(2122, response.getTotalLowercaseAlphaChars());
        assertEquals(48, response.getTotalUppercaseAlphaCharts());
        assertEquals(400, response.getTotalWhiteSpace());
        assertEquals(43, response.getTotalPunctuationSpace());
        assertEquals(395, response.getTotalWords());
    }
}