package uz.hayot.camunda.tasks.dto.camunda.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Value(staticConstructor = "of")
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = ExceptionData.ExceptionDataBuilder.class)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionData {

    String key;

    String value;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ExceptionDataBuilder {
    }

}
