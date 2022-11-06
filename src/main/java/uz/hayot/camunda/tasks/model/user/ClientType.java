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
@Table(name = "client_type", schema = "camunda")
public class ClientType {
    @Id
    @Column("client_id")
    Long id;

    @Column("name_ru")
    String nameRu;

    @Column("name_en")
    String nameEn;

    @Column("name_uz")
    String nameUz;
}
