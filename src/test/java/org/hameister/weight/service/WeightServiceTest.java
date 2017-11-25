package org.hameister.weight.service;

import org.hameister.weight.WeightReader;
import org.hameister.weight.data.WeightDataRepository;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by hameister on 28.10.17.
 */

public class WeightServiceTest {

    @Test
    public void  testdataShouldContain3Datasets() throws IOException {
        WeightDataRepository weightDataRepository = mock(WeightDataRepository.class);


        WeightService weightService = new WeightService(new WeightReader(Paths.get("./src/test/resources/Testdata.txt")), weightDataRepository);

        assertThat(weightService.numberOfLines(), is(3));
    }

    @Test
    public void avgShouldComputeTheAvagerateWeight() throws IOException {
        WeightDataRepository weightDataRepository = mock(WeightDataRepository.class);


        WeightService weightService = new WeightService(new WeightReader(Paths.get("./src/test/resources/Testdata.txt")), weightDataRepository);
        assertThat(weightService.getAvg(), is(80.36666870117188d));
    }
}
