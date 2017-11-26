package org.hameister.weight.service;

import org.hameister.weight.WeightReader;
import org.hameister.weight.data.WeightData;
import org.hameister.weight.data.WeightDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by hameister on 28.10.17.
 */
@Service
public class WeightService {


    private WeightDataRepository weightDataRepository;

    @Autowired
    public WeightService(WeightDataRepository dataRepository) {
        Assert.notNull(dataRepository, "Repository should not be null");
        this.weightDataRepository = dataRepository;
    }

    public int numberOfLines() {
        return  weightDataRepository.findAll().size();
    }

    public  Double getAvg() {
        return weightDataRepository.findAll().stream().mapToDouble(WeightData::getWeight).average().orElse(Double.NaN);
    }

    public List<WeightData> getDataSets() {
        return  weightDataRepository.findAll();
    }
}
