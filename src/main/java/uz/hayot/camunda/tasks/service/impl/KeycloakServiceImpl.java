package uz.hayot.camunda.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.SocialLinkRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Service;
import uz.hayot.camunda.tasks.configuration.keycloak.KeycloakHelper;
import uz.hayot.camunda.tasks.exception.UserActionException;
import uz.hayot.camunda.tasks.model.user.User;
import uz.hayot.camunda.tasks.repository.ClientTypeRepository;
import uz.hayot.camunda.tasks.repository.UserRepository;
import uz.hayot.camunda.tasks.service.KeycloakService;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final KeycloakHelper keycloakHelper;
    private final Keycloak keycloak;
    private final ClientTypeRepository clientTypeRepository;
    private final JdbcAggregateTemplate jdbcTemplate;


    private final UserRepository userRepository;

    @Override
    public Optional<UserRepresentation> findUserByUsername(String username) {
        return Optional.ofNullable(keycloak.realm(keycloakHelper.realm())
                .users()
                .get(username)
                .toRepresentation());
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
        try {
            Response response = keycloak.realm(keycloakHelper.realm())
                    .users()
                    .create(userRepresentation);
            if (response.getStatus() != 201)
                throw new UserActionException("Ошибка при создании клиента на стороне сервиса авторизации");
            String userId = CreatedResponseUtil.getCreatedId(response);
            user.setId(UUID.fromString(userId));
            if (user.getDocNumber() > 0 && user.getDocSeries().length() > 1)
                clientTypeRepository.findById(2L).ifPresent(user::setType);
            else clientTypeRepository.findById(1L).ifPresent(user::setType);
            log.debug("Create User: {}", user);
            return jdbcTemplate.(user);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Caught Exception Creating User: {}, ERROR:{}", user, e.getMessage());
            throw new UserActionException(String.format("Пользователь не создан, ошибка: %s", e.getMessage()));
        }
    }


    public void create() {

    }

}
