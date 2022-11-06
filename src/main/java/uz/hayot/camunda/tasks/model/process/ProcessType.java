package uz.hayot.camunda.tasks.model.process;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "bpmn_process_type", schema = "camunda")
public class ProcessType implements Serializable {

    @Id
    @Column(name = "type_id")
    Long id;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column(name = "name_uz")
    String nameUz;

    @OneToMany(targetEntity = ProcessCategory.class)
    Set<ProcessCategory> categories;
}
