package uz.ucell.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.user.SexType;

@Repository
public interface SexTypeRepository extends JpaRepository<SexType,Long> {
}
