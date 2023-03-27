package sk.umb.fpv.laflait.subsection.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.subsection.persistance.entity.SubsectionEntity;

@Repository
public interface SubsectionRepository extends CrudRepository<SubsectionEntity, Long> {

}
