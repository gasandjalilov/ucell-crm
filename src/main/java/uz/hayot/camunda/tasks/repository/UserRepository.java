package uz.hayot.camunda.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.hayot.camunda.tasks.model.user.User;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
}
