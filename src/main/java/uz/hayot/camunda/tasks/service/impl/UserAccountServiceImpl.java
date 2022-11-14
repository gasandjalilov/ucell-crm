package uz.hayot.camunda.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.hayot.camunda.tasks.model.account.UserAccount;
import uz.hayot.camunda.tasks.model.process.ProcessType;
import uz.hayot.camunda.tasks.repository.ProcessTypeRepository;
import uz.hayot.camunda.tasks.repository.UserAccountRepository;
import uz.hayot.camunda.tasks.service.UserAccountService;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public List<UserAccount> getAll() {
        return StreamSupport.stream(userAccountRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public List<UserAccount> getUserAccounts(UUID userId) {
        return userAccountRepository.retrieveAccounts(userId);
    }

    @Override
    public UserAccount add(UserAccount process) {
        return userAccountRepository.save(process);
    }

    @Override
    public UserAccount update(UserAccount process) {
        return null;
    }
}
