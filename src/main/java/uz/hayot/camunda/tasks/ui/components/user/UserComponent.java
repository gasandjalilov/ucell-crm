package uz.hayot.camunda.tasks.ui.components.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.command.CreateProcessInstanceCommandStep1;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.hayot.camunda.tasks.service.KeycloakService;
import uz.hayot.camunda.tasks.ui.MainUserInterface;

import javax.annotation.security.PermitAll;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@PermitAll
@Route(value = "users", layout = MainUserInterface.class)
@Component
@Slf4j
@UIScope
@PageTitle("Пользователи")
public class UserComponent extends VerticalLayout {

    private final KeycloakService keycloakService;
    private final String nibbd;
    private final ZeebeClient zeebeClient;

    final ObjectMapper mapper = new ObjectMapper();

    public UserComponent(KeycloakService keycloakService,
                         ZeebeClient zeebeClient,
                         @Value("${bpm.client.nibbd}") String nibbd) {
        this.keycloakService = keycloakService;
        this.zeebeClient = zeebeClient;
        this.nibbd=nibbd;
        add(upperSearchElement());
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
            createTask();
        });
        Button addUser = new Button("Создать Пользователя", new Icon(VaadinIcon.USER));
        addUser.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addUser.setWidth(15,Unit.PERCENTAGE);
        addUser.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(CreateUserComponent.class));
        horizontalLayout.add(textField,search,addUser);
        return horizontalLayout;
    }

    public void createTask(){
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(nibbd)
                .latestVersion()
                .withResult()
                .requestTimeout(Duration.ofMinutes(5))
                .send()
                .whenCompleteAsync((processInstanceResult, throwable) -> {
                    log.info("Variables: {}", processInstanceResult.getVariables());
                });



    }



}
