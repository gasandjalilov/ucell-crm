package uz.hayot.camunda.tasks.service;

import uz.hayot.camunda.tasks.model.account.UserAccount;
import uz.hayot.camunda.tasks.model.process.Process;

import java.util.List;
import java.util.UUID;

public interface UserAccountService {

    List<UserAccount> getUserAccounts(UUID userId);

    UserAccount add(UserAccount process);

    UserAccount update(UserAccount process);
}
