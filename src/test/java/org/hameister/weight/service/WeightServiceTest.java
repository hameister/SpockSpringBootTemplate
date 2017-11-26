package org.hameister.weight.service;

import org.hameister.weight.WeightReader;
import org.hameister.weight.data.WeightData;
import org.hameister.weight.data.WeightDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hameister on 28.10.17.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WeightServiceTest {

    @Test
    public void testdataShouldContain3Datasets() throws IOException {
        WeightDataRepository weightDataRepository = mock(WeightDataRepository.class);
        when(weightDataRepository.findAll()).thenReturn(getTestdata());

        WeightService weightService = new WeightService(weightDataRepository);

        assertThat(weightService.numberOfLines(), is(3));
    }

    @Test
    public void avgShouldComputeTheAvagerateWeight() throws IOException {
        WeightDataRepository weightDataRepository = mock(WeightDataRepository.class);
        when(weightDataRepository.findAll()).thenReturn(getTestdata());

        WeightService weightService = new WeightService(weightDataRepository);
        assertThat(weightService.getAvg(), is(80.36666870117188d));
    }

    public List<WeightData> getTestdata() {
        return Arrays.asList(new WeightData(80.3f, LocalDate.of(2017, 8, 23)),
                new WeightData(80.5f, LocalDate.of(2017, 8, 24)),
                new WeightData(80.3f, LocalDate.of(2017, 8, 25)));
    }
}
