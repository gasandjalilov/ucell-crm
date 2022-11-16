package uz.ucell.tasks.model.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import uz.ucell.tasks.dto.serializer.NumericBooleanSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@NonNull
@AllArgsConstructor
@Entity
@Table(name = "user_data", schema = "camunda")
public class User{
    @Id
    @Column(name = "user_id")
    UUID id;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "patronymic")
    String patronymic;

    @JoinColumn(name = "doc_id",
            referencedColumnName = "doc_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    DocType docType;

    @Column(name = "doc_series")
    String docSeries;

    @Column(name = "doc_number")
    String docNumber;

    @Transient
    String phoneMain;

    @Column(name = "additional_phone_number")
    String phoneAdditional;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    LocalDate birthDate;

    @Column(name = "inn")
    String inn;

    @Column(name = "pinfl")
    String pinfl;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sex_id",
            referencedColumnName = "sex_id")
    SexType sex;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id",
            referencedColumnName = "client_id")
    ClientType type;

    @Column(name = "address")
    String address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "region_id",
            referencedColumnName = "region_id")
    Region region;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id",
            referencedColumnName = "country_id")
    Country country;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "district_id",
            referencedColumnName = "district_id")
    District district;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_issue")
    LocalDate dateIssue;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_expiry")
    LocalDate dateExpiry;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "nationality_id",
            referencedColumnName = "nationality_id")
    Nationality nationality;

    @Column(name = "nci_id")
    String nciId;

    @Column(name = "birth_place")
    String birthPlace;

    @Column(name = "registration_place")
    String registrationPlace;

    @Column(name = "created_on")
    @CreationTimestamp
    LocalDateTime createdOn;

    @Column(name = "created_by")
    UUID createdBy;

    @Column(name = "updated_on")
    @UpdateTimestamp
    LocalDateTime updatedOn;

    @Column(name = "updated_by")
    UUID updatedBy;

    @Column(name = "resident")
    @JsonSerialize(using = NumericBooleanSerializer.class)
    Boolean resident=true;
}
