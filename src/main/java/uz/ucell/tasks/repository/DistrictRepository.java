package uz.ucell.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.user.District;

@Repository
public interface DistrictRepository extends CrudRepository<District,String> {
}
