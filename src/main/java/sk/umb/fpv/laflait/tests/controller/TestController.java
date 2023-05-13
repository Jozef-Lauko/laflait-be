package sk.umb.fpv.laflait.tests.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.fpv.laflait.tests.service.TestDetailDTO;
import sk.umb.fpv.laflait.tests.service.TestService;

import java.util.List;

@RestController
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/api/testy")
    public List<TestDetailDTO> getTests() {
        System.out.println("*** GET ALL TESTS ***");

        return testService.getAllTests();
    }
}
