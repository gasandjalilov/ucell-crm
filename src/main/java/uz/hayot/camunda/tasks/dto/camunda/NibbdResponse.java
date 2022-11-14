package uz.hayot.camunda.tasks.dto.camunda;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value(staticConstructor = "of")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NibbdResponse implements Serializable {

    @JsonProperty("inn")
    Long inn;
    @JsonProperty("sex")
    Integer sex;
    @JsonProperty("pinfl")
    String pinfl;
    @JsonProperty("region")
    String region;
    @JsonProperty("userId")
    String userId;
    @JsonProperty("address")
    String address;
    @JsonProperty("country")
    Integer country;
    @JsonProperty("success")
    Integer success;
    @JsonProperty("surname")
    String surname;
    @JsonProperty("district")
    String district;
    @JsonProperty("lastName")
    String lastName;
    @JsonProperty("patronym")
    String patronym;
    @JsonProperty("userTask")
    Integer userTask;
    @JsonProperty("birthDate")
    String birthDate;
    @JsonProperty("dateIssue")
    String dateIssue;
    @JsonProperty("firstName")
    String firstName;
    @JsonProperty("givePlace")
    String givePlace;
    @JsonProperty("givenname")
    String givenname;
    @JsonProperty("dateExpiry")
    String dateExpiry;
    @JsonProperty("nationality")
    String nationality;
    @JsonProperty("documentNumber")
    String documentNumber;
    @JsonProperty("documentSeries")
    String documentSeries;
    @JsonProperty("birth_place")
    String birthPlace;
}
