package uz.hayot.camunda.tasks.dto.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Component {
    @JsonProperty("label")
    private String label;
    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("validate")
    private Validate validate;
    @JsonProperty("properties")
    private Properties properties;
    @JsonProperty("description")
    private String description;
    @JsonProperty("defaultValue")
    private String defaultValue;
    @JsonProperty("disabled")
    private Boolean disabled;
    @JsonProperty("values")
    private List<Value> values = null;
    @JsonProperty("text")
    private String text;
    @JsonProperty("action")
    private String action;

}
