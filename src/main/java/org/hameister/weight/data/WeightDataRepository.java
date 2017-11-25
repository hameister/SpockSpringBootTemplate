package org.hameister.weight.data;



import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by hameister on 18.11.17.
 */
@org.springframework.stereotype.Repository
public interface WeightDataRepository extends Repository<WeightData, Long> {
    List<WeightData> findAll();
    List<WeightData> findByDate(LocalDate localDate);
    WeightData save(WeightData item);
}
