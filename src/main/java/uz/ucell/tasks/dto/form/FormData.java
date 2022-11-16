package uz.ucell.tasks.dto.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormData {
    @JsonProperty("components")
    private List<Component> components = new ArrayList<>();
    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("executionPlatform")
    private String executionPlatform;
    @JsonProperty("executionPlatformVersion")
    private String executionPlatformVersion;
    @JsonProperty("exporter")
    private Exporter exporter;
    @JsonProperty("schemaVersion")
    private Integer schemaVersion;

}
