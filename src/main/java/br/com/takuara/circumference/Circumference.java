package br.com.takuara.circumference;

import br.com.takuara.framework.security.Role;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "cincumferences")
public class Circumference extends PanacheEntity implements Serializable {

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

    @Column(name = "right_Calf")
    private Double rightCalf;

}
