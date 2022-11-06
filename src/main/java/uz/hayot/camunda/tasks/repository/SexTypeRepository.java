package uz.hayot.camunda.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.hayot.camunda.tasks.model.user.SexType;

@Repository
public interface SexTypeRepository extends JpaRepository<SexType,Long> {
}
