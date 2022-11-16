package uz.ucell.tasks.service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import uz.ucell.tasks.model.user.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<User> getAll();

    User add(User user);

    User add(User user, UI ui, Dialog notification);

    List<User> find(String username);
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

    List<Nationality> nationalityTypes();

    Optional<Nationality> nationalityType(String id);


}
