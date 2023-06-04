package sk.umb.fpv.laflait.testQuestions.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sk.umb.fpv.laflait.testQuestions.service.TestQuestionDetailDTO;
import sk.umb.fpv.laflait.testQuestions.service.TestQuestionRequestDTO;
import sk.umb.fpv.laflait.testQuestions.service.TestQuestionService;

import java.util.List;

@RestController
public class TestQuestionController {

    private TestQuestionService testQuestionService;

    public TestQuestionController(TestQuestionService testQuestionService){ this.testQuestionService = testQuestionService; }

    @GetMapping("/api/test/{testID}")
    public List<TestQuestionDetailDTO> getQuestionsByTestID(@PathVariable Long testID) {
        System.out.println("*** GET ALL QUESTIONS BY TEST ID ***");

        return testQuestionService.getQuestionsByTestID(testID);
    }

    @PostMapping("/api/test/{testID}")
    public void submitTestQuestions(@Valid @RequestBody TestQuestionRequestDTO testQuestionRequestDTO, @PathVariable Long testID) {
        testQuestionService.processAnswers(testID, testQuestionRequestDTO);
    }

    @GetMapping("/api/test/answers/{testID}")
    public List<Object[]> getCorrectAnswers(@PathVariable Long testID) {
        System.out.println(testQuestionService.getCorrectAnswersByID(testID).get(0));
        return testQuestionService.getCorrectAnswersByID(testID);
    }
}
