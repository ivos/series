package series;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<Double> input = SeriesFile.load("hodiny.csv");
        List<Double> trend = Series.trend(input, 12, distance -> (1 - 0.06 * distance));
        List<Double> cycle = Series.cycle(input, trend, 12, 6, distance -> (1 - 0.1 * distance));
        SeriesFile.save("trend.csv", trend);
        SeriesFile.save("cycle.csv", cycle);

//        List<Double> cyclePercentTrend = Series.cyclePercentTrend(input, trend, 12, 6, distance -> (1 - 0.1 * distance));
//        List<List<Double>> inMonths = new ArrayList<>(12);
//        for (int index = 0; index < 12; index++) {
//            inMonths.add(new ArrayList<>());
//        }
//        for (int index = 0; index < cyclePercentTrend.size(); index++) {
//            int month = (index + 2) % 12;
//            inMonths.get(month).add(cyclePercentTrend.get(index));
//        }
//        for (int month = 0; month < 12; month++) {
//            List<Double> monthPcts = inMonths.get(month);
//            SeriesFile.save("monthPcts" + (month + 1) + ".csv", monthPcts);
//        }
    }
}
