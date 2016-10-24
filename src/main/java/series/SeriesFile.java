package series;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SeriesFile {

    public static List<Double> load(String fileName) {
        try {
            List<String> lines = FileUtils.readLines(new File(fileName));
            return lines.stream()
                    .map(line -> line.replaceAll("[ \\p{Space}]", ""))
                    .map(line -> line.replace(",", "."))
                    .map(Double::valueOf)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(String fileName, List<Double> series) {
        try {
            List<String> lines = series.stream()
                    .map(value -> value.toString())
                    .map(line -> line.replace(".", ","))
                    .collect(Collectors.toList());
            FileUtils.writeLines(new File(fileName), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
