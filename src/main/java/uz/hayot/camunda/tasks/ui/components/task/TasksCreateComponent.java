package uz.hayot.camunda.tasks.ui.components.task;


import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.hayot.camunda.tasks.configuration.camunda.TaskConfiguration;
import uz.hayot.camunda.tasks.model.process.ProcessType;
import uz.hayot.camunda.tasks.service.ProcessTypeService;
import uz.hayot.camunda.tasks.ui.MainUserInterface;

import javax.annotation.security.PermitAll;
import java.util.List;

@PermitAll
@Route(value = "tasks/new", layout = MainUserInterface.class)
@Component
@Slf4j
@UIScope
@PageTitle("Создать Задачу")
public class TasksCreateComponent extends VerticalLayout {

    private final TaskConfiguration taskConfiguration;
    private final ProcessTypeService processTypeService;


    public TasksCreateComponent(TaskConfiguration taskConfiguration, ProcessTypeService processTypeService) {
        this.taskConfiguration = taskConfiguration;
        this.processTypeService = processTypeService;

        add(this.taskForm());
    }


    public FormLayout taskForm(){
        FormLayout formLayout = new FormLayout();
        HorizontalLayout main = new HorizontalLayout();
        formLayout.add(main);
        ComboBox<ProcessType> checkbox =new ComboBox();
        List<ProcessType> processList = processTypeService.getAll();
        log.info("Processes: {}",processList.size());
        checkbox.setItems(processList);
        checkbox.setItemLabelGenerator(ProcessType::getNameRu);
        return formLayout;
    }
}
