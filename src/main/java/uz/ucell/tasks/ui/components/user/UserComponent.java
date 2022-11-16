package uz.ucell.tasks.ui.components.user;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.ucell.tasks.model.user.User;
import uz.ucell.tasks.service.UserService;
import uz.ucell.tasks.ui.MainUserInterface;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "users", layout = MainUserInterface.class)
@Component
@Slf4j
@UIScope
@PageTitle("Пользователи")
public class UserComponent extends VerticalLayout {

    private final UserService userService;
    private final UI ui = UI.getCurrent();
    Grid<User> userCrud = new Grid<>(User.class,false);


    public UserComponent(
            UserService userService) {
        this.userService = userService;
        add(upperSearchElement());
        userCrud.addColumn(User::getId).setHeader("User ID");
        userCrud.addColumn(User::getLastname).setHeader("Lastname");
        userCrud.addColumn(User::getFirstname).setHeader("Firstname");
        userCrud.addColumn(User::getNciId).setHeader("NCI ID");
        userCrud.addColumn(User::getDocSeries).setHeader("Document Series");
        userCrud.addColumn(User::getDocNumber).setHeader("Document Number");
        add(userCrud);
    }


    public HorizontalLayout upperSearchElement(){
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();

        TextField textField = new TextField();
        textField.setPlaceholder("Поиск по номеру телефона, пример (99893*******)");
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        textField.setWidth(70, Unit.PERCENTAGE);
        Button search = new Button("Поиск",new Icon(VaadinIcon.SEARCH));
        search.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        search.setWidth(15,Unit.PERCENTAGE);
        search.addClickListener(buttonClickEvent -> {
                ui.access(() -> userCrud.setItems(userService.find(textField.getValue())));
        });
        Button addUser = new Button("Создать Пользователя", new Icon(VaadinIcon.USER));
        addUser.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addUser.setWidth(15,Unit.PERCENTAGE);
        addUser.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(CreateUserComponent.class));
        horizontalLayout.add(textField,search,addUser);
        return horizontalLayout;
    }





}
