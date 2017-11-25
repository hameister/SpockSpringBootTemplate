package org.hameister.weight.service

import org.hameister.weight.WeightReader
import org.hameister.weight.data.WeightDataRepository

import java.nio.file.Paths

class WeightServiceSpec extends spock.lang.Specification {

    def "The Test data file should contain 3 datasets"() {
        given: "StatisticService and Repository Mock"

        WeightDataRepository dataRepository = Mock(WeightDataRepository)
        String pathToTestdata = "./src/test/resources/Testdata.txt"
        WeightReader weightReader = new  WeightReader(Paths.get(pathToTestdata));

        when: "WeightService is created and lines are read"
        WeightService weightService = new WeightService(weightReader, dataRepository);

        then: "The number of lines should be 3"
        weightService.numberOfLines() == 3
    }

    def "Avg should compute the average weight"() {
        given: "WeightService is created and lines are read"
        String pathToTestdata = "./src/test/resources/Testdata.txt"
        WeightDataRepository dataRepository = Mock(WeightDataRepository)
        WeightService weightService = new WeightService(new WeightReader(Paths.get(pathToTestdata)), dataRepository);

        expect: "The Average weight is computed"
        weightService.avg == 80.36666870117188d

    }
}