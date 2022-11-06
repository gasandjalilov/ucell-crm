package uz.hayot.camunda.tasks.model.user;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sex_type", schema = "camunda")
public class SexType{
    @Id
    @Column(name = "sex_id")
    Long id;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column(name = "name_uz")
    String nameUz;


}
