package org.hameister.weight;


import org.hameister.weight.data.WeightData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by hameister on 28.10.17.
 */

@Component
public class WeightReader {

    private Logger logger = LoggerFactory.getLogger(WeightReader.class);

    private List<String> lines;
    private List<WeightData> weightDatas = new ArrayList<>();

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy", Locale.GERMAN);

    //Default file
    public String data = "Weightdata.txt";

    public WeightReader()  {
    }

    public void readFile() throws IOException {
        logger.info("Reading file:" + data);
        Path path = Paths.get(data);

        System.out.println("Start reading..." + data);

        lines = Files.readAllLines(path);
    }

    public WeightReader(Path path) throws IOException {
        lines = Files.readAllLines(path);
    }

    public List<String> getLines() {
        return lines;
    }


    public List<WeightData> getWeightDatas() {
        lines.stream().forEach(l -> weightDatas.add(parseLine(l)));
        return weightDatas;
    }

    public WeightData parseLine(String line) {
        if (line != null) {
            String weight = line.substring(0, 4);
            String date = line.substring(5) + "2017";
            LocalDateTime localDate = LocalDate.parse(date, format).atStartOfDay();

            if (weight.indexOf(',') > 0) {
                weight = weight.replace(',', '.');
            }

            return new WeightData(Float.parseFloat(weight), localDate.toLocalDate());
        }


        return new WeightData(10.0f, LocalDate.now());
    }
}
