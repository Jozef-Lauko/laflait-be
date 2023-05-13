package sk.umb.fpv.laflait.testQuestions.persistance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.testQuestions.persistance.entity.TestQuestionEntity;

import java.util.List;

@Repository
public interface TestQuestionRepository extends CrudRepository<TestQuestionEntity, Long> {

    @Query("SELECT q FROM Otazky q WHERE q.testID = :testID")
    List<TestQuestionEntity> findAllByTest(@Param("testID") Long testID);
}
