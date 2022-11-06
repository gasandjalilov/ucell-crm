package uz.hayot.camunda.tasks.model.process;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Set;

@Value(staticConstructor = "of")
@RequiredArgsConstructor
@NonNull
@Builder
@Table(name = "bpmn_process_category", schema = "camunda")
public class ProcessCategory implements Serializable {

    @Id
    @Column("category_id")
    Long id;

    @Column("name_ru")
    String nameRu;

    @Column("name_en")
    String nameEn;

    @Column("name_uz")
    String nameUz;

    @MappedCollection(keyColumn = "category_id", idColumn = "category_id")
    final Set<Process> processes;

}
