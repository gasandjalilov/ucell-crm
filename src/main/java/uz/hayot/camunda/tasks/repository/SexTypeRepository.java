package uz.hayot.camunda.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.hayot.camunda.tasks.model.user.SexType;

@Repository
public interface SexTypeRepository extends CrudRepository<SexType,Long> {
}
