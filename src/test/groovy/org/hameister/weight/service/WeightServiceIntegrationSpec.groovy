package org.hameister.weight.service

import org.hameister.weight.data.WeightData
import org.hameister.weight.data.WeightDataRepository
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate
import java.time.format.DateTimeFormatter


/**
 * Created by hameister on 27.11.17.
 */
class WeightServiceIntegrationSpec extends Specification {

    def "Get weight at one date with parameterized tests"() {
        given: "Repository with Testdata"
        WeightDataRepository dataRepository = Mock(WeightDataRepository)
        dataRepository.findByDate(date) >> new WeightData(weight, date)
        WeightService weightService = new WeightService(dataRepository);


        when: "WeightService is created and lines are read"
        float weightFromDate = weightService.getWeightFromDate(date)

        then: "The weight at this day"
        weightFromDate == weight
        where: "Load data"
        weight | date
        80.3f  | LocalDate.of(2017, 8, 23)
        80.5f  | LocalDate.of(2017, 8, 24)
        80.3f  | LocalDate.of(2017, 8, 25)

    }


    def "Get weight at one date with parameters from encapsulated method getTestData"() {
        given: "Repository with Testdata"
        WeightDataRepository dataRepository = Mock(WeightDataRepository)
        dataRepository.findByDate(date) >> new WeightData(weight, date)
        WeightService weightService = new WeightService(dataRepository);


        when: "WeightService is created and lines are read"
        float weightFromDate = weightService.getWeightFromDate(date)

        then: "The weight at this day"
        weightFromDate == weight
        where: "Load data"
        // ArrayList [Float, LocalDate]
        [weight, date] << getTestdata()
    }

    //Create Multi-Variable Data Pipes for Groovy Spock test in a method.
    def getTestdata() {
        return [[80.3f, LocalDate.of(2017, 8, 23)], [80.5f, LocalDate.of(2017, 8, 24)]]
    }


    def "Get weight at one date with parameters from file"() {
        given: "Repository with Testdata"
        WeightDataRepository dataRepository = Mock(WeightDataRepository)
        dataRepository.findByDate(date) >> new WeightData(weight, date)
        WeightService weightService = new WeightService(dataRepository);


        when: "WeightService is created and lines are read"
        float weightFromDate = weightService.getWeightFromDate(date)

        then: "The weight at this day"
        weightFromDate == weight
        where: "Load data"
        // ArrayList [float, LocalDate]
        // ->  Or call getTestdataFromFile()
        [weight, date] << getTestdataFromFileShort()
    }

    //Create Multi-Variable Data Pipes for Groovy Spock test from a file.
    def getTestdataFromFile() {

        String testdata = new File("src/test/resources/Testdata.txt").text
        ArrayList data = [];

        List<String> lines = testdata.tokenize("\n")

        for (String line : lines) {
            float weight = Float.parseFloat(line.substring(0, 4).replace(',','.'));
            def date = line.substring(5) + "2017";
            data.add([weight, LocalDate.parse(date,DateTimeFormatter.ofPattern('dd.M.yyyy'))])
        }
        return data
    }


    //Create Multi-Variable Data Pipes for Groovy Spock test from a file.
    //Shorter version with a closure
    def getTestdataFromFileShort() {
        ArrayList data = [];
        new File("src/test/resources/Testdata.txt").text.eachLine {line->data.add([Float.parseFloat(line.substring(0, 4).replace(',','.')), LocalDate.parse(line.substring(5) + "2017",DateTimeFormatter.ofPattern('dd.M.yyyy'))]) }

        return data
    }
}