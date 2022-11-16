package uz.ucell.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.process.ProcessCategory;

@Repository
public interface ProcessCategoryRepository extends CrudRepository<ProcessCategory,Long> {
}
