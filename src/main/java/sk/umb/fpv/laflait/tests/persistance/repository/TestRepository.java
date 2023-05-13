package sk.umb.fpv.laflait.tests.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.fpv.laflait.tests.persistance.entity.TestEntity;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, Long> {

}
