package uz.hayot.camunda.tasks.dto.camunda;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import uz.hayot.camunda.tasks.dto.serializer.LocalDateDeserializer;
import uz.hayot.camunda.tasks.dto.serializer.LocalDateSerializer;
import uz.hayot.camunda.tasks.dto.serializer.NumericBooleanSerializer;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
@NonNull
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = OpenIdClientRequest.OpenIdClientRequestBuilder.class)
public class OpenIdClientRequest {

    String branch="00980";
    String name;
    String codeCountry;
    String codeType;
    @JsonSerialize(using = NumericBooleanSerializer.class)
    Boolean codeResident;
    String codeSubject="1";
    String signRegistr;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate birthday;

    String postAddress;

    String passportType;

    String passportSerial;

    String passportNumber;

    String passportPlaceRegistration;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate passportDateRegistration;

    String codeBank="860";

    String codeCitizenship;

    String birthPlace;

    String codeAdrRegion;

    String codeAdrDistrict;

    String phoneHome;

    String phoneMobile;

    String typeDocument;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate passportDateExpiration;

    String pinfl;

    String family;

    String firstName;

    String patronymic;

    String codeGender;

    String codeNation;

    String codeBirthRegion;

    String codeBirthDistr;

    String codeTaxOrg="1";

    String numberTaxRegistration;

    String codeCapacity;

    String pensionSertifSerial;

    String numCertifCapacity;

    String emailAddress;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate capacityStatusDate=LocalDate.now();

    String capacityStatusPlace;

    String signVip;

    String closeType="1";

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate closeDocN=LocalDate.now();

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate closeDocD=LocalDate.now();

    String drivePermitSer;

    String drivePermitNum;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate drivePermitRegD=LocalDate.now();

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate drivePermitExpD=LocalDate.of(2070,12,31);

    String drivePermitPlace;

    String agreement;

    String zipCode;

    String contractNum;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class OpenIdClientRequestBuilder {
    }

}
