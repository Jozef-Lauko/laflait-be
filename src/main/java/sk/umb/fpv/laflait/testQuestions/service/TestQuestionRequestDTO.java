package sk.umb.fpv.laflait.testQuestions.service;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TestQuestionRequestDTO {

    @NotEmpty(message = "Answers must not be empty.")
    private String[] answer;
    @NotNull(message = "userId must not be null.")
    private Long userId;
    @NotEmpty(message = "userId must not be null.")
    private Long[] questionId;

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long[] questionId) {
        this.questionId = questionId;
    }
}
