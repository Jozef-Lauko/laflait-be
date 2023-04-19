package sk.umb.fpv.laflait.section.persistance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;

import java.util.List;

@Repository
public interface SectionRepository extends CrudRepository<SectionEntity, Long> {
    @Query("SELECT k FROM Kapitoly k WHERE k.thesesID = :thesesID")
    List<SectionEntity> findAllByTeza(@Param("thesesID") Long thesesID);
}


