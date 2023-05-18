package sk.umb.fpv.laflait.grades.persistance.entity;

import jakarta.persistence.*;
import sk.umb.fpv.laflait.authentication.persistance.entity.UserEntity;
import sk.umb.fpv.laflait.tests.persistance.entity.TestEntity;

@Entity(name = "Znamky")
@Table(name = "znamky")
public class GradesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_znamka")
    private Long id;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_test")
    private TestEntity testEntity;

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
}
