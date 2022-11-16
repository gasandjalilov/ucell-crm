package uz.ucell.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.user.Region;

@Repository
public interface RegionRepository extends CrudRepository<Region,String> {
}
