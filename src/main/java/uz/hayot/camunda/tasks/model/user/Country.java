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
@Table(name = "country", schema = "camunda")
public class Country {
    @Id
    @Column("country_id")
    String id;

    @Column("name_ru")
    String nameRu;

    @Column("name_en")
    String nameEn;

    @Column("name_uz")
    String nameUz;
}
