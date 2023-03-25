package sk.umb.fpv.laflait.theses.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;

@Repository
public interface ThesesRepository extends CrudRepository<ThesesEntity, Long> {

}
