package sk.umb.fpv.laflait.testQuestions.persistance.entity;

import jakarta.persistence.*;

@Entity(name = "Otazky")
@Table(name = "otazky")
public class TestQuestionEntity {

    @Id
    @Column(name = "id_otazka")
    private Long id;

    @Column(name = "id_test")
    private Long testID;

    @Column(name = "otazka_opis")
    private String questionDespricption;

    @Column(name = "spravna_odpoved")
    private String correctAnswer;

    @Column(name = "ans1")
    private String ans1;

    @Column(name = "ans2")
    private String ans2;

    @Column(name = "ans3")
    private String ans3;

    @Column(name = "ans4")
    private String ans4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionDespricption() {
        return questionDespricption;
    }

    public void setQuestionDespricption(String questionDespricption) {
        this.questionDespricption = questionDespricption;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public Long getTestID() {
        return testID;
    }

    public void setTestID(Long testID) {
        this.testID = testID;
    }
}
