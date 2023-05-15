package sk.umb.fpv.laflait.userAnswer.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.userAnswer.persistance.entity.UserAnswerEntity;

@Repository
public interface UserAnswerRepository extends CrudRepository<UserAnswerEntity, Long> {
}
