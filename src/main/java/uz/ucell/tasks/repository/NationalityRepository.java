package uz.ucell.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.user.Nationality;

@Repository
public interface NationalityRepository extends CrudRepository<Nationality,String> {
}
