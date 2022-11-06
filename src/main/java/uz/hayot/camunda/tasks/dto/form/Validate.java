package uz.hayot.camunda.tasks.dto.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Validate {
    @JsonProperty("required")
    private Boolean required;
    @JsonProperty("minLength")
    private Integer minLength;
    @JsonProperty("pattern")
    private String pattern;
    @JsonProperty("min")
    private Integer min;
    @JsonProperty("max")
    private Integer max;
    @JsonProperty("maxLength")
    private Integer maxLength;
}
