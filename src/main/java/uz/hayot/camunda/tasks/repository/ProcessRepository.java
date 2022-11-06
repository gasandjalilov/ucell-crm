package uz.hayot.camunda.tasks.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.hayot.camunda.tasks.model.process.Process;

@Repository
public interface ProcessRepository extends CrudRepository<Process,String> {
}
