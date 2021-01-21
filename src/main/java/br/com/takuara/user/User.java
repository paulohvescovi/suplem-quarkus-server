package br.com.takuara.user;

import br.com.takuara.enumerations.Genre;
import br.com.takuara.framework.converters.UserRolesConverter;
import br.com.takuara.framework.security.Role;
import br.com.takuara.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"email"}, callSuper = true)
public class User extends PanacheEntity implements Serializable {

    @Email(message = "Invalid email")
    @Column(name = "email", unique = true, nullable = false, length = 100)
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    @NotBlank(message = "Password is mandatory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Convert(converter = UserRolesConverter.class)
    @Column(name = "roles", length = 200, nullable = false)
    @NotNull(message = "roles of the user is mandatory")
    private List<Role> roles = Collections.singletonList(Role.USER);

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 50)
    private Genre genre;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "body_weight")
    private Double bodyWeight;

    @Column(name = "body_fat")
    @Range(max = 100, message = "Body fat percentage must be between 0 and 100")
    private Double bodyFat;

    @JsonGetter
    public Integer age(){
        return birthDate != null
                ? DateUtils.yearsBetween(birthDate, LocalDate.now())
                : null;
    }

}
