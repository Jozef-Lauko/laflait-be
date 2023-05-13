package sk.umb.fpv.laflait.testQuestions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.fpv.laflait.testQuestions.service.TestQuestionDetailDTO;
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
}
