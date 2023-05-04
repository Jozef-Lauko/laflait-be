package sk.umb.fpv.laflait.subsection.persistance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.subsection.persistance.entity.SubsectionEntity;

@Repository
public interface SubsectionRepository extends CrudRepository<SubsectionEntity, Long> {
    @Query("SELECT k FROM Podkapitoly k WHERE k.sectionID = :sectionID")
    Iterable<SubsectionEntity> findAllByKapitola(@Param("sectionID") Long sectionID);
}
