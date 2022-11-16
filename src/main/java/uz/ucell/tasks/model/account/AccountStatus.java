package uz.ucell.tasks.model.account;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account_status", schema = "camunda")
public class AccountStatus implements Serializable {

    @Id
    @Column(name = "status_id")
    Long id;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column(name = "name_uz")
    String nameUz;
}
