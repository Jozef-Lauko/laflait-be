package sk.umb.fpv.laflait.userAnswer.persistance.entity;

import jakarta.persistence.*;
import sk.umb.fpv.laflait.authentication.persistance.entity.UserEntity;
import sk.umb.fpv.laflait.testQuestions.persistance.entity.TestQuestionEntity;
import sk.umb.fpv.laflait.tests.persistance.entity.TestEntity;

import java.util.Optional;

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

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_test")
    private TestEntity testEntity;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_otazka")
    private TestQuestionEntity testQuestionEntity;

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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    public TestQuestionEntity getTestQuestionEntity() {
        return testQuestionEntity;
    }

    public void setTestQuestionEntity(TestQuestionEntity testQuestionEntity) {
        this.testQuestionEntity = testQuestionEntity;
    }
}
