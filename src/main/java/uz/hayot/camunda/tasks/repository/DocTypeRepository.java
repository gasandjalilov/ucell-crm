package uz.hayot.camunda.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.hayot.camunda.tasks.model.user.DocType;

@Repository
public interface DocTypeRepository extends CrudRepository<DocType,Long> {
}
