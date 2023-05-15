package sk.umb.fpv.laflait.testQuestions.service;

import jakarta.validation.constraints.NotBlank;

public class TestQuestionRequestDTO {

    @NotBlank(message = "Answers must not be empty.")
    private String[] answer;
    @NotBlank(message = "userId must not be empty.")
    private Long userId;
    @NotBlank(message = "questionId must not be empty.")
    private Long questionId;

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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
