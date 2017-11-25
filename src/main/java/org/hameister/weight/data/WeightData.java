package org.hameister.weight.data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by hameister on 29.10.17.
 */

@Entity
@Table(name = "WeightData")
public class WeightData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private  Long id;

    @Column(name = "weight")
    private float weight;

    @Column(name = "date")
    private LocalDate date;

    public WeightData() {
    }

    public WeightData(float weight, LocalDate date) {
        this.weight = weight;
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public LocalDate getDate() {
        return date;
    }

}
