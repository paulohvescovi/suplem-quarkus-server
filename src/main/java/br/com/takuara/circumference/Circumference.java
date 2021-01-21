package br.com.takuara.circumference;

import br.com.takuara.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "cincumferences")
public class Circumference extends PanacheEntity implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is mandatory")
    private User user;

    @Column(name = "neck")
    private Double neck;

    @Column(name = "shoulder")
    private Double shoulder;

    @Column(name = "chest")
    private Double chest;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "glutes")
    private Double glutes;

    @Column(name = "left_biceps")
    private Double leftBiceps;

    @Column(name = "right_biceps")
    private Double rightBiceps;

    @Column(name = "left_forearm")
    private Double leftForearm;

    @Column(name = "right_forearm")
    private Double rightForearm;

    @Column(name = "left_thigh")
    private Double leftThigh;

    @Column(name = "right_thigh")
    private Double rightThigh;

    @Column(name = "left_calf")
    private Double leftCalf;

    @Column(name = "right_calf")
    private Double rightCalf;

}
