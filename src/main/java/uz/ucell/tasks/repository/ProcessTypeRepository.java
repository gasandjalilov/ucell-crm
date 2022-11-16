package uz.ucell.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.process.ProcessType;

@Repository
public interface ProcessTypeRepository extends CrudRepository<ProcessType,Long> {
}
