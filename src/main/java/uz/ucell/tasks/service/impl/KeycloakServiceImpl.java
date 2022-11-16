package uz.ucell.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import uz.ucell.tasks.configuration.keycloak.KeycloakHelper;
import uz.ucell.tasks.configuration.keycloak.SecurityService;
import uz.ucell.tasks.exception.UserActionException;
import uz.ucell.tasks.model.user.User;
import uz.ucell.tasks.model.user.UserMapper;
import uz.ucell.tasks.repository.ClientTypeRepository;
import uz.ucell.tasks.repository.UserRepository;
import uz.ucell.tasks.service.KeycloakService;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final KeycloakHelper keycloakHelper;
    private final Keycloak keycloak;
    private final ClientTypeRepository clientTypeRepository;

    private final SecurityService securityService;

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserRepresentation> findUserByUsername(String username) {
        return keycloak.realm(keycloakHelper.realm())
                .users()
                .search(username);
    }

    @Override
    public List<UserRepresentation> findAll() {
        return keycloak.realm(keycloakHelper.realm())
                .users()
                .list();
    }

    @Override
    public Optional<UserRepresentation> findByAttribute() {
        return Optional.empty();
    }

    @Override
    public User createUser(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setEnabled(false);
        userRepresentation.setUsername(user.getPhoneMain().toString());
        Response response = keycloak.realm(keycloakHelper.realm())
                .users()
                .create(userRepresentation);
        if(response.getStatus() == 409)
            throw new UserActionException("Ошибка при создании клиента,данный номер уже зарегистрирован ранее");
        if (response.getStatus() != 201)
            throw new UserActionException("Ошибка при создании клиента на стороне сервиса авторизации");
        String userId = CreatedResponseUtil.getCreatedId(response);
        user.setId(UUID.fromString(userId));
        if (user.getDocNumber().length()>0 && user.getDocSeries().length() > 1)
            clientTypeRepository.findById(2L).ifPresent(user::setType);
        else clientTypeRepository.findById(1L).ifPresent(user::setType);
        log.debug("Create User: {}", user);
        user.setCreatedBy(UUID.fromString(Objects.requireNonNull(securityService.getAuthenticatedUser().getAttribute("sub"))));
        return user;
    }

    @Override
    public void deleteUser(String id) {
        Response response = keycloak.realm(keycloakHelper.realm())
                .users()
                .delete(id);

    }


}
