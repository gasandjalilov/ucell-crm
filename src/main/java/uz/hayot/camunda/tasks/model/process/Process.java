package uz.hayot.camunda.tasks.model.process;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "bpmn_process", schema = "camunda")
public class Process {

    @Id
    @Column(name = "process_id")
    String processId;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column(name = "name_uz")
    String nameUz;

    @Column(name = "state")
    State state;

    @Column(name = "executable")
    Boolean executable;
}
