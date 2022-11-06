package uz.hayot.camunda.tasks.model.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "client_type", schema = "camunda")
public class ClientType{
    @Id
    @Column(name = "client_id")
    Long id;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column(name = "name_uz")
    String nameUz;

}
