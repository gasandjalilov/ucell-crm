package uz.hayot.camunda.tasks.dto.camunda;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainResponse<T> {
    private int status;
    private String message;
    private T data;

}