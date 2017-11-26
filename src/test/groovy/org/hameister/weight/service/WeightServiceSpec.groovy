package org.hameister.weight.service

import org.hameister.weight.WeightReader
import org.hameister.weight.data.WeightData
import org.hameister.weight.data.WeightDataRepository

import java.nio.file.Paths
import java.time.LocalDate

class WeightServiceSpec extends spock.lang.Specification {

    def "The Test data file should contain 3 datasets"() {
        given: "StatisticService and Repository Mock"

        WeightDataRepository dataRepository = Mock(WeightDataRepository)
        dataRepository.findAll() >> getTestdata()


        when: "WeightService is created and lines are read"
        WeightService weightService = new WeightService(dataRepository);

        then: "The number of lines should be 3"
        weightService.numberOfLines() == 3
    }

    def "Avg should compute the average weight"() {
        given: "WeightService is created and lines are read"
        WeightDataRepository dataRepository = Mock(WeightDataRepository)
        dataRepository.findAll() >> getTestdata()
        WeightService weightService = new WeightService(dataRepository);

        expect: "The Average weight is computed"
        weightService.avg == 80.36666870117188d

    }


    def getTestdata() {
        return Arrays.asList(new WeightData(80.3f, LocalDate.of(2017, 8, 23)),
                new WeightData(80.5f, LocalDate.of(2017, 8, 24)),
                new WeightData(80.3f, LocalDate.of(2017, 8, 25)));
    }
}