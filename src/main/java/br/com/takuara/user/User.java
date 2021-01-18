package br.com.takuara.user;

import br.com.takuara.framework.converters.UserRolesConverter;
import br.com.takuara.framework.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"email"})
public class User extends PanacheEntity implements Serializable {

    @Email(message = "Invalid email")
    @Column(name = "email", unique = true, nullable = false, length = 100)
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    @NotBlank(message = "Password is mandatory")
    @JsonIgnore
    private String password;

    @Convert(converter = UserRolesConverter.class)
    @Column(name = "roles", length = 200, nullable = false)
    @NotBlank(message = "roles of the user is mandatory")
    private List<Role> roles = new ArrayList<>();

}
