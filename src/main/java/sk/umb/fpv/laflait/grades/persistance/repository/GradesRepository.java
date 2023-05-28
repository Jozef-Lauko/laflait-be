package sk.umb.fpv.laflait.grades.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.grades.persistance.entity.GradesEntity;

@Repository
public interface GradesRepository extends CrudRepository<GradesEntity, Long> {
}
