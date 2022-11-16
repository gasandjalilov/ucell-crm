package uz.ucell.tasks.model.account;


import lombok.Data;
import uz.ucell.tasks.model.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_account", schema = "camunda")
public class UserAccount implements Serializable {

    @Id
    @Column(name = "account_id")
    String id;

    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    User user;
}
