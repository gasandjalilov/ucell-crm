package uz.hayot.camunda.tasks.service;

import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;
import uz.hayot.camunda.tasks.dto.camunda.NibbdResponse;
import uz.hayot.camunda.tasks.model.process.ProcessType;
import uz.hayot.camunda.tasks.model.user.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<User> getAll();

    User add(User user);

    User update(User user);

    User disable(UUID id);

    List<SexType> sexTypes();

    Optional<SexType> sexType(Long id);

    List<District> districts();

    Optional<District> district(String id);

    List<Region> regions();

    Optional<Region> region(String id);

    List<Country> countries();

    Optional<Country> country(String id);

    List<DocType> docTypes();

    Optional<DocType> docType(Long id);

    User update(NibbdResponse response, User user);

}
