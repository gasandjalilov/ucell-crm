package uz.ucell.tasks.dto.user;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value(staticConstructor = "of")
@RequiredArgsConstructor
@NonNull
@Builder
public class UserDTO implements Serializable {

    String firstname;

    String lastname;


}
