package uz.ucell.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.user.ClientType;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType,Long> {
}
