package sk.umb.fpv.laflait.userAnswer.persistance.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.userAnswer.persistance.entity.UserAnswerEntity;

import java.util.List;

@Repository
public interface UserAnswerRepository extends CrudRepository<UserAnswerEntity, Long> {
    @Modifying
    @Query("DELETE FROM User_odpovede u WHERE u.userId = :userID AND u.testId = :testID")
    void deleteAllByIdTestAndIdUser(@Param("userID") Long userID, @Param("testID") Long testID);

    @Query("SELECT u FROM User_odpovede u WHERE u.userId = :userID AND u.testId = :testID")
    List<UserAnswerEntity> getAllByIdTestAndIdUser(@Param("userID") Long userID, @Param("testID") Long testID);

}
