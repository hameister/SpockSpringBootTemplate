package org.hameister.weight;

import org.hameister.weight.data.WeightData;
import org.hameister.weight.data.WeightDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

/**
 * Created by hameister on 18.11.17.
 */
@Component
public class WeightDataImporter {

    private Logger logger = LoggerFactory.getLogger(WeightDataImporter.class);

    private WeightDataRepository weightDataRepository;

    private WeightReader weightReader;

    @Autowired
    public WeightDataImporter(WeightDataRepository repository, WeightReader weightReader) {
        Assert.notNull(repository, "Repository should not be null");
        this.weightDataRepository = repository;
        this.weightReader = weightReader;
        importData();
    }


    private void importData()  {

        logger.info("Start Import");

        try {
            weightReader.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<WeightData> weightDatas = weightReader.getWeightDatas();

        weightDatas.stream().forEach(weightData -> weightDataRepository.save(weightData));

        logger.info("Imported " + weightDataRepository.findAll().size() + " Datasets");

    }
}
