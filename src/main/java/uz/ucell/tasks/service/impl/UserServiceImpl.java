package uz.ucell.tasks.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.progressbar.ProgressBar;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.ucell.tasks.exception.UserActionException;
import uz.hayot.camunda.tasks.model.user.*;
import uz.hayot.camunda.tasks.repository.*;
import uz.hayot.ucell.tasks.model.user.*;
import uz.hayot.ucell.tasks.repository.*;
import uz.ucell.tasks.model.user.*;
import uz.ucell.tasks.repository.*;
import uz.ucell.tasks.service.KeycloakService;
import uz.ucell.tasks.service.UserAccountService;
import uz.ucell.tasks.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final KeycloakService keycloakService;
    private final UserRepository userRepository;
    private final SexTypeRepository sexTypeRepository;
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final CountryRepository countryRepository;
    private final DocTypeRepository docTypeRepository;
    private final NationalityRepository nationalityRepository;
    private final UserMapper userMapper;
    private final ZeebeClient zeebeClient;
    private final String nciProcess;

    private final UserAccountService userAccountService;

    final ObjectMapper mapper = new ObjectMapper();

    public UserServiceImpl(KeycloakService keycloakService,
                           UserRepository userRepository,
                           SexTypeRepository sexTypeRepository,
                           RegionRepository regionRepository,
                           DistrictRepository districtRepository,
                           CountryRepository countryRepository,
                           DocTypeRepository docTypeRepository,
                           NationalityRepository nationalityRepository,
                           UserMapper userMapper,
                           ZeebeClient zeebeClient,
                           @Value("${bpm.client.nci}") String nciProcess, UserAccountService userAccountService) {
        this.keycloakService = keycloakService;
        this.userRepository = userRepository;
        this.sexTypeRepository = sexTypeRepository;
        this.regionRepository = regionRepository;
        this.districtRepository = districtRepository;
        this.countryRepository = countryRepository;
        this.docTypeRepository = docTypeRepository;
        this.nationalityRepository = nationalityRepository;
        this.userMapper = userMapper;
        this.zeebeClient = zeebeClient;
        this.nciProcess = nciProcess;
        this.userAccountService = userAccountService;
    }

    @Override
    public List<User> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public User add(User user) {
        return keycloakService.createUser(user);
    }

    @Override
    public User add(User user, UI ui, Dialog notification) {
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE));
        closeButton.setVisible(false);
        closeButton.addClickListener(event -> {
            notification.close();
        });
        Span span = new Span();
        ProgressBar progressBar = new ProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(true);
        notification.add(span, progressBar);
        notification.getFooter().add(closeButton);
        ui.access(notification::open);
        try {
            User kc = userRepository.save(keycloakService.createUser(user));
            ui.access(() -> {
                span.setText(String.format("Клиент Создан: %s", kc.getPhoneMain()));
                progressBar.setVisible(false);
                closeButton.setVisible(true);
            });
            return kc;
        } catch (Exception e) {
            ui.access(() -> {
                span.setText(String.format("Клиент Не Создан: %s", e.getMessage()));
                progressBar.setVisible(false);
                closeButton.setVisible(true);
            });
            return null;
        }
    }

    @Override
    public List<User> find(String username) {
        List<UserRepresentation> userRepresentation = keycloakService.findUserByUsername(username);
        if (userRepresentation.isEmpty()) throw new UserActionException("Пользователь не найден");
        return userRepository.findAllById(userRepresentation.stream().map(user -> UUID.fromString(user.getId())).collect(Collectors.toList()));
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    //TODO
    @Override
    public User disable(UUID id) {
        return null;
    }

    @Override
    public List<SexType> sexTypes() {
        return StreamSupport.stream(sexTypeRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<SexType> sexType(Long id) {
        return sexTypeRepository.findById(id);
    }

    @Override
    public List<District> districts() {
        return StreamSupport.stream(districtRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<District> district(String id) {
        return districtRepository.findById(id);
    }

    @Override
    public List<Region> regions() {
        return StreamSupport.stream(regionRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<Region> region(String id) {
        return regionRepository.findById(id);
    }

    @Override
    public List<Country> countries() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<Country> country(String id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<DocType> docTypes() {
        return StreamSupport.stream(docTypeRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<DocType> docType(Long id) {
        return docTypeRepository.findById(id);
    }

    @Override
    public List<Nationality> nationalityTypes() {
        return StreamSupport.stream(nationalityRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<Nationality> nationalityType(String id) {
        return nationalityRepository.findById(id);
    }




}
