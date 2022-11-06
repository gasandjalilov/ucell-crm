package uz.hayot.camunda.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.hayot.camunda.tasks.model.process.ProcessCategory;

@Repository
public interface ProcessCategoryRepository extends CrudRepository<ProcessCategory,Long> {
}
