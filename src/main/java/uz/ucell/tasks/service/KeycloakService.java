package uz.ucell.tasks.service;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import uz.ucell.tasks.model.user.User;

import java.util.List;
import java.util.Optional;

@Service
public interface KeycloakService {

    List<UserRepresentation> findUserByUsername(String username);

    List<UserRepresentation> findAll();

    Optional<UserRepresentation> findByAttribute();

    User createUser(User user);

    void deleteUser(String username);
}
