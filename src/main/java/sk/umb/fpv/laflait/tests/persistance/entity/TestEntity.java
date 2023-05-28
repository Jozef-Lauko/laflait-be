package sk.umb.fpv.laflait.tests.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Testy")
@Table(name = "testy")
public class TestEntity {

    @Id
    @Column(name = "id_test")
    private Long id;

    @Column(name = "test_opis")
    private String testDescription;

    @Column(name = "id_teza")
    private Long thesisId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public Long getThesisId() {
        return thesisId;
    }

    public void setThesisId(Long thesisId) {
        this.thesisId = thesisId;
    }
}
