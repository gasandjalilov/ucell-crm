package uz.ucell.tasks.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.process.Process;

@Repository
public interface ProcessRepository extends CrudRepository<Process,String> {
}
