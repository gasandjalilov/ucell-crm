package uz.hayot.camunda.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.hayot.camunda.tasks.model.user.District;

@Repository
public interface DistrictRepository extends CrudRepository<District,String> {
}
