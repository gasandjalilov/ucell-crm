package uz.hayot.camunda.tasks.model.user;

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
@Table(name = "district", schema = "camunda")
public class District {
    @Id
    @Column("district_id")
    String id;

    @Column("name_ru")
    String nameRu;

    @Column("name_en")
    String nameEn;

    @Column("name_uz")
    String nameUz;
}
