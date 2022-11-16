package uz.ucell.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.user.DocType;

@Repository
public interface DocTypeRepository extends CrudRepository<DocType,Long> {
}
