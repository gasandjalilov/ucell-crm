package uz.ucell.tasks.service;

import uz.ucell.tasks.model.account.UserAccount;

import java.util.List;
import java.util.UUID;

public interface UserAccountService {

    List<UserAccount> getUserAccounts(UUID userId);

    UserAccount add(UserAccount process);

    UserAccount update(UserAccount process);
}
