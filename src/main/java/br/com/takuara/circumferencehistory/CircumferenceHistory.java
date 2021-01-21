package br.com.takuara.circumferencehistory;

import br.com.takuara.circumference.Circumference;
import br.com.takuara.enumerations.CircumferenceFields;
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
    private CircumferenceFields field;

    @Column(name = "value")
    private Double value;

    @Column(name = "date_insert")
    private LocalDateTime dateInsert = LocalDateTime.now();

}
