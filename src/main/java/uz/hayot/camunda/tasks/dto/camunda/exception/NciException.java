package uz.hayot.camunda.tasks.dto.camunda.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.util.List;

@Getter
@Value(staticConstructor = "of")
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = NciException.NciExceptionBuilder.class)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NciException {
    Integer code;

    String name;

    List<ExceptionData> param;

    String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class NciExceptionBuilder {
    }

}
