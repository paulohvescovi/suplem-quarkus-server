package br.com.takuara.circumferencehistory;

import br.com.takuara.enumeration.CircumferenceFields;
import br.com.takuara.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "cincumferences_history")
public class CircumferenceHistory extends PanacheEntity implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is mandatory")
    private User user;

    @Column(name = "field")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "circumference field is mandatory")
    private CircumferenceFields field;

    @Column(name = "value")
    @NotNull(message = "the circumference field value is mandatory")
    private Double value;

    @Column(name = "date_insert")
    @NotNull(message = "date of insert is mandatory")
    private LocalDateTime dateInsert = LocalDateTime.now();

}
