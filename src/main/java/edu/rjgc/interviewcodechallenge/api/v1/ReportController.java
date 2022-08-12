package edu.rjgc.interviewcodechallenge.api.v1;

import edu.rjgc.interviewcodechallenge.models.Request;
import edu.rjgc.interviewcodechallenge.models.Response;
import edu.rjgc.interviewcodechallenge.service.WordCounterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ReportController {

    private final WordCounterService wordCounterService;

    public ReportController(WordCounterService wordCounterService) {
        this.wordCounterService = wordCounterService;
    }

    @PostMapping("/word-counter")
    public Response report(@RequestBody Request request) {
        return wordCounterService.buildReport(request);
    }
}
