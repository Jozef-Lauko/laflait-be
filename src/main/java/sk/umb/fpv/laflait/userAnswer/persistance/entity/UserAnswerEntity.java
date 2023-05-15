package sk.umb.fpv.laflait.userAnswer.persistance.entity;

import jakarta.persistence.*;

@Entity(name = "User_odpovede")
@Table(name = "user_odpovede")
public class UserAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_odpoved")
    private Long id;

    @Column(name = "user_odpoved_text")
    private String answer;

    @Column(name = "hodnotenie")
    private String result;

    @Column(name = "id_user")
    private Long userId;

    @Column(name = "id_otazka")
    private Long questionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
