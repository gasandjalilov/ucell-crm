package uz.hayot.camunda.tasks.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@NonNull
@AllArgsConstructor
@Table(name = "user_data", schema = "camunda")
public class User implements Serializable {
    @Id
    @Column("user_id")
    UUID id;

    @Column("firstname")
    String firstname;

    @Column("lastname")
    String lastname;

    @Column("patronymic")
    String patronymic;

    @Column("doc_id")
    DocType docType;

    @Column("doc_series")
    String docSeries;

    @Column("doc_number")
    Long docNumber;

    @Column("main_phone_number")
    Long phoneMain;

    @Column("additional_phone_number")
    Long phoneAdditional;

    @Column("birth_date")
    LocalDate birthDate;

    @Column("inn")
    Long inn;

    @Column("pinfl")
    Long pinfl;

    @Column("sex_id")
    SexType sex;

    @Column("client_id")
    ClientType type;

    @Column("address")
    String address;

    @Column("region_id")
    Region region;

    @Column("country_id")
    Country country;

    @Column("district_id")
    District district;

    @Column("date_issue")
    LocalDate dateIssue;

    @Column("date_expiry")
    LocalDate dateExpiry;

    @Column("nationality")
    String nationality;


}
