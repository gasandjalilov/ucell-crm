package uz.hayot.camunda.tasks.model.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "nationality_type", schema = "camunda")
public class Nationality {
    @Id
    @Column(name = "nationality_id")
    String id;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column(name = "name_uz")
    String nameUz;


}
