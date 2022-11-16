package uz.ucell.tasks.model.user;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "country", schema = "camunda")
public class Country {

    @Id
    @Column(name = "country_id")
    String id;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column(name = "name_uz")
    String nameUz;


}
