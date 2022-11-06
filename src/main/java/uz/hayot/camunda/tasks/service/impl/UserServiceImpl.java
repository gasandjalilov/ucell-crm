package uz.hayot.camunda.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.hayot.camunda.tasks.dto.camunda.NibbdResponse;
import uz.hayot.camunda.tasks.model.user.*;
import uz.hayot.camunda.tasks.repository.*;
import uz.hayot.camunda.tasks.service.KeycloakService;
import uz.hayot.camunda.tasks.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final KeycloakService keycloakService;
    private final UserRepository userRepository;
    private final SexTypeRepository sexTypeRepository;
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final CountryRepository countryRepository;
    private final DocTypeRepository docTypeRepository;
    private final UserMapper userMapper;


    @Override
    public List<User> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public User add(User user) {
        return keycloakService.createUser(user);
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
    public User update(NibbdResponse response, User user) {
        User finalUser = userMapper.update(response, user);
        district(response.getDistrict()).ifPresent(finalUser::setDistrict);
        region(response.getRegion()).ifPresent(finalUser::setRegion);
        country(response.getCountry().toString()).ifPresent(finalUser::setCountry);
        sexType(response.getSex().longValue()).ifPresent(finalUser::setSex);
        log.info("Nibbd Response: {}", response);
        return finalUser;
    }
}
