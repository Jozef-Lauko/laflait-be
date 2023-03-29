package sk.umb.fpv.laflait.section.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;

@Repository
public interface SectionRepository extends CrudRepository<SectionEntity, Long> {
}
