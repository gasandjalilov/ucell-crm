package uz.ucell.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.ucell.tasks.model.account.UserAccount;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {


    @Query(nativeQuery = true,
            value = "select * " +
                    "from user_account " +
                    "where user_id= ?1")
    List<UserAccount> retrieveAccounts(UUID userId);
}
