package uz.ucell.tasks.model.process;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "bpmn_process_category", schema = "camunda")
public class ProcessCategory implements Serializable {

    @Id
    @Column(name = "category_id")
    Long id;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column(name = "name_uz")
    String nameUz;

    @OneToMany(targetEntity = Process.class)
    Set<Process> processes;

}
