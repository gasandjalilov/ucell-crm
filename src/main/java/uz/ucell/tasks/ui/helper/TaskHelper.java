package uz.ucell.tasks.ui.helper;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import io.camunda.tasklist.dto.Variable;
import org.springframework.stereotype.Component;
import uz.ucell.tasks.dto.form.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Component
public class TaskHelper {

    public com.vaadin.flow.component.Component generateComponent(uz.ucell.tasks.dto.form.Component component,
                                                                 Map<String, Object> variables,
                                                                 Optional<Variable> var){
         if (component.getType().equals("number")) {
            TextField numberField = new TextField(component.getLabel());
             numberField.setLabel(component.getLabel());
             var.ifPresent(variable -> numberField.setValue(variable.getValue().toString()));
             if(component.getValidate()!=null)numberField.setRequired(component.getValidate().getRequired());
             numberField.addValueChangeListener(textFieldStringComponentValueChangeEvent -> {
                variables.put(component.getKey(), textFieldStringComponentValueChangeEvent.getValue());
            });
            return numberField;
        } else if (component.getType().equals("select")) {
             ComboBox<Value> dropdown = new ComboBox<Value>(component.getLabel());
             if(component.getValues()!=null)dropdown.setItems(component.getValues());
             dropdown.setItemLabelGenerator(Value::getLabel);
             var.ifPresent(variable -> dropdown.setValue(component.getValues().stream().filter(value -> value.getValue()==variable.getValue()).findFirst().orElse(new Value("",""))));
             if(component.getValidate()!=null)dropdown.setRequired(component.getValidate().getRequired());
             dropdown.addValueChangeListener(comboBoxValueComponentValueChangeEvent -> {
                 variables.put(component.getKey(), comboBoxValueComponentValueChangeEvent.getValue().getValue());
             });
             return dropdown;
         } else if (component.getType().equals("radio") ) {
             Checkbox checkbox = new Checkbox(component.getLabel());
             checkbox.addValueChangeListener(checkboxBooleanComponentValueChangeEvent -> {
                if(checkboxBooleanComponentValueChangeEvent.getValue()) variables.put(component.getKey(), 1);
                else variables.put(component.getKey(), 0);
             });
             return checkbox;
         } else if (component.getType().equals("checkbox") ) {
             Checkbox checkbox = new Checkbox(component.getLabel());
             checkbox.addValueChangeListener(checkboxBooleanComponentValueChangeEvent -> variables.put(component.getKey(),checkboxBooleanComponentValueChangeEvent.getValue()));
             return checkbox;
         } else if(component.getType().equals("text")){
             TextArea textArea = new TextArea(component.getLabel());
             var.ifPresent(variable -> textArea.setValue(variable.getValue().toString()));
             textArea.setValue(component.getText());
             textArea.setReadOnly(true);
             return textArea;
         }
         else if(component.getType().equals("button")){
             Button button = new Button(component.getLabel());
             return button;
         }
         else if(component.getType().equals("date")){
             DatePicker datePicker = new DatePicker(component.getLabel());
             DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
             singleFormatI18n.setDateFormat("yyyy-MM-dd");
             datePicker.setI18n(singleFormatI18n);
             datePicker.addValueChangeListener(datePickerLocalDateComponentValueChangeEvent -> variables.put(component.getKey(),datePicker.getValue().toString()));
             if(component.getValidate()!=null) datePicker.setRequired(component.getValidate().getRequired());
             var.ifPresent(variable -> {
                 LocalDate localDate = LocalDate.parse(variable.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                 datePicker.setValue(localDate);
             });
             return datePicker;
         }
         else {
             TextField textField = new TextField(component.getLabel());
             textField.setLabel(component.getLabel());
             var.ifPresent(variable -> textField.setValue(variable.getValue().toString()));
             if(component.getValidate()!=null)textField.setRequired(component.getValidate().getRequired());
             textField.addValueChangeListener(textFieldStringComponentValueChangeEvent -> {
                 variables.put(component.getKey(), textFieldStringComponentValueChangeEvent.getValue());
             });
             return textField;
         }

    }


}
