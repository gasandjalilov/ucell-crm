package uz.ucell.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.account.AccountStatus;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus,Long> {

}
