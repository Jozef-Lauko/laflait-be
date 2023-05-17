package sk.umb.fpv.laflait.grades.persistance.entity;

import jakarta.persistence.*;

@Entity(name = "Znamky")
@Table(name = "znamky")
public class GradesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_znamka")
    private Long id;

    @Column(name = "id_test")
    private Long testId;

    @Column(name = "id_user")
    private Long userId;

    @Column(name = "znamka_text")
    private String grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
