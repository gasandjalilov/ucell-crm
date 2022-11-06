package uz.hayot.camunda.tasks.ui.components.task;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.dto.Form;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.hayot.camunda.tasks.configuration.camunda.TaskConfiguration;
import uz.hayot.camunda.tasks.configuration.keycloak.SecurityService;
import uz.hayot.camunda.tasks.dto.form.FormData;
import uz.hayot.camunda.tasks.ui.MainUserInterface;
import uz.hayot.camunda.tasks.ui.helper.TaskHelper;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PermitAll
@Route(value = "tasks", layout = MainUserInterface.class)
@Component
@Slf4j
@UIScope
@PageTitle("Задачи")
public class TasksComponent extends VerticalLayout {

    private final TaskConfiguration taskConfiguration;
    private final SecurityService securityService;
    private final TaskHelper taskHelper;
    ObjectMapper mapper = new ObjectMapper();


    public TasksComponent(TaskConfiguration taskConfiguration, SecurityService securityService, TaskHelper taskHelper) throws TaskListException {

        this.taskConfiguration = taskConfiguration;
        this.securityService = securityService;
        this.taskHelper = taskHelper;
        List<Task> tasks = null;
        CamundaTaskListClient client = taskConfiguration.client();
        log.info("Client: {}", client.getTaskListUrl());
        List<Task> taskList = new ArrayList<>(client.getAssigneeTasks(securityService.getAuthenticatedUser().getPreferredUsername(), TaskState.CREATED, 100));
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button button = new Button("Создать Задачу",new Icon(VaadinIcon.PLUS));
        horizontalLayout.setPadding(true);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        horizontalLayout.setWidthFull();
        horizontalLayout.add(new H2("Задачи"));
        horizontalLayout.add(button);
        horizontalLayout.addClickListener(horizontalLayoutClickEvent -> UI.getCurrent().navigate(TasksCreateComponent.class));
        add(horizontalLayout);

        Grid<Task> tasksGrid = new Grid<Task>();
        tasksGrid.addColumn(Task::getId).setHeader("Id Процесса");
        tasksGrid.addColumn(Task::getProcessName).setHeader("Название Процесса");
        tasksGrid.addColumn(Task::getTaskState).setHeader("Статус");
        tasksGrid.addColumn(Task::getCreationTime).setHeader("Дата Создания");
        tasksGrid.setItems(taskList);
        add(tasksGrid);

        tasksGrid.addItemClickListener(taskItemClickEvent -> {
            Dialog dialog = new Dialog();
            FormLayout formLayout = new FormLayout();
            Map<String, Object> variables = new HashMap<>();
            FormData formData;
            try {
                Form form = client.getForm(taskItemClickEvent.getItem().getFormKey().substring(taskItemClickEvent.getItem().getFormKey().lastIndexOf(":")+1), taskItemClickEvent.getItem().getProcessDefinitionId());
                formData = mapper.readValue(form.getSchema(),FormData.class);
            } catch (TaskListException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            formData.getComponents().forEach(variable -> {
                formLayout.add(taskHelper.generateComponent(variable,variables,
                        taskItemClickEvent.getItem().getVariables().stream().filter(var -> var.getName().equals(variable.getKey())).findFirst()));
            });
            Button confirm = new Button("Завершить");
            confirm.addClickListener(buttonClickEvent -> {
                log.info("Variables: {}", variables);
                try {
                    client.completeTask(taskItemClickEvent.getItem().getId(), variables);
                } catch (TaskListException e) {
                    throw new RuntimeException(e);
                }
                dialog.close();
                tasksGrid.getDataProvider().refreshAll();
            });

            formLayout.add(confirm);
            dialog.add(formLayout);
            dialog.open();

        });


    }
}