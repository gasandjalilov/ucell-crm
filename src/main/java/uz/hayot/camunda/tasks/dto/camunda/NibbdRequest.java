package uz.hayot.camunda.tasks.dto.camunda;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.hayot.camunda.tasks.model.user.User;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value(staticConstructor = "of")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NibbdRequest implements Serializable {

    private Integer userTask;

    private String birthDate;

    private String documentNumber;

    private String documentSeries;

    private UUID userId;


    public NibbdRequest(User user, UUID userId) {
        this.birthDate=user.getBirthDate().format(DateTimeFormatter.ISO_DATE);
        this.documentNumber=user.getDocNumber();
        this.documentSeries=user.getDocSeries();
        this.userTask=0;
        this.userId=userId;
    }
}
