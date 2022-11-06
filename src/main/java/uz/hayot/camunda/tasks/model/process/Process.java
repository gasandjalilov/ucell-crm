package uz.hayot.camunda.tasks.model.process;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value(staticConstructor = "of")
@RequiredArgsConstructor
@NonNull
@Builder
@Table(name = "bpmn_process", schema = "camunda")
public class Process {

    @Id
    @Column("process_id")
    String processId;

    @Column("name_ru")
    String nameRu;

    @Column("name_en")
    String nameEn;

    @Column("name_uz")
    String nameUz;

    @Column("state")
    State state;

    @Column("executable")
    Boolean executable;
}
