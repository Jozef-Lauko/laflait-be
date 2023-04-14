package sk.umb.fpv.laflait.notes.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;

@Repository
public interface NotesRepository extends CrudRepository<NotesEntity, Long> {
}
