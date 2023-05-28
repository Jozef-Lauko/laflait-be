package sk.umb.fpv.laflait.userAnswer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.fpv.laflait.userAnswer.service.UserAnswerDTO;
import sk.umb.fpv.laflait.userAnswer.service.UserAnswerService;

import java.util.List;

@RestController
public class UserAnswerController {

    private UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService){
        this.userAnswerService = userAnswerService;
    }

    @GetMapping("/api/odpovede")
    public List<UserAnswerDTO> getAllAnswers(){
        System.out.println("*** GET ALL ANSWERS ***");

        return userAnswerService.getAllAnswers();
    }
}
