package series;

import java.util.ArrayList;
import java.util.List;

public class Series {

    public static double round(double value) {
        return Math.round(value * 100) / 100.;
    }

    public static double average(List<Double> values, List<Double> weights) {
        if (values.size() != weights.size()) {
            throw new RuntimeException(
                    "Invalid number of weights " + weights.size() + " for " + values.size() + " values.");
        }
        double totalValue = 0, totalWeight = 0;
        for (int i = 0; i < values.size(); i++) {
            totalValue += values.get(i) * weights.get(i);
            totalWeight += weights.get(i);
        }
        return round(totalValue / totalWeight);
    }

    /**
     * Based on inCycle returns index, index-1, index+1, index-2, index+2, etc.
     * Wraps around limits.
     * Returns null in case of overflow.
     */
    public static Integer trendIndex(int index, int size, int cycleLength, int inCycle) {
        int offset = (1 + inCycle) / 2 * (1 == inCycle % 2 ? -1 : 1);
        int result = index + offset;
        if (result < 0) {
            result = cycleLength / 2 - offset;
        } else if (result >= size) {
            result = size - 1 - cycleLength / 2 - offset;
        }
        if (result < 0 || result >= size) {
            return null;
        }
        return result;
    }

    public static double floatingAverage(
            List<Double> input, int cycleLength, WeightFunction weightFunction, int index) {
        List<Double> values = new ArrayList<>(cycleLength + 1);
        List<Double> weights = new ArrayList<>(cycleLength + 1);
        List<Integer> indexes = new ArrayList<>();
        for (int inCycle = 0; inCycle < cycleLength + 1; inCycle++) {
            Integer trendIndex = trendIndex(index, input.size(), cycleLength, inCycle);
            if (null != trendIndex && !indexes.contains(trendIndex)) {
                indexes.add(trendIndex);
                values.add(input.get(trendIndex));
                weights.add(weightFunction.weight(Math.abs(trendIndex - index)));
            }
        }
        return average(values, weights);
    }

    public static List<Double> trend(List<Double> input, int cycleLength, WeightFunction weightFunction) {
        List<Double> result = new ArrayList<>(input.size() + cycleLength);
        for (int index = 0; index < input.size(); index++) {
            result.add(floatingAverage(input, cycleLength, weightFunction, index));
        }
        double lastTrend = result.get(input.size() - 1);
        double lastDiff = lastTrend - result.get(input.size() - 2);
        for (int index = 0; index < cycleLength; index++) {
            result.add(round(lastTrend + (1 + index) * lastDiff));
        }
        return result;
    }

    public static List<Double> cyclePercents(List<Double> input, List<Double> trend) {
        List<Double> percents = new ArrayList<>(input.size());
        for (int index = 0; index < input.size(); index++) {
            double percent = (input.get(index) - trend.get(index)) / trend.get(index) * 100;
            percents.add(percent);
        }
        return percents;
    }

    public static List<List<Double>> sameInCycle(List<Double> input, int cycleLength) {
        List<List<Double>> result = new ArrayList<>(cycleLength);
        for (int inCycle = 0; inCycle < cycleLength; inCycle++) {
            ArrayList<Double> inCycleList = new ArrayList<>();
            result.add(inCycleList);
            for (int index = 0; index < input.size(); index++) {
                if (index % cycleLength == inCycle) {
                    inCycleList.add(input.get(index));
                }
            }
        }
        return result;
    }

    public static List<Double> cyclePercentTrend(List<Double> input, List<Double> trend, int cycleLength,
                                                 int metaCycleLength, WeightFunction weightFunction) {
        List<Double> percents = cyclePercents(input, trend);
        List<List<Double>> sameInCycle = sameInCycle(percents, cycleLength);
        List<Double> percentTrend = new ArrayList<>(input.size());
        for (int index = 0; index < input.size(); index++) {
            int inCycle = index % cycleLength;
            int metaIndex = index / cycleLength;
            List<Double> inCycleInput = sameInCycle.get(inCycle);
            percentTrend.add(floatingAverage(inCycleInput, metaCycleLength, weightFunction, metaIndex));
        }
        return percentTrend;
    }

    public static List<Double> cycle(List<Double> input, List<Double> trend, int cycleLength,
                                     int metaCycleLength, WeightFunction weightFunction) {
        List<Double> percentTrend = cyclePercentTrend(input, trend, cycleLength, metaCycleLength, weightFunction);
        List<Double> result = new ArrayList<>(input.size() + cycleLength);
        for (int index = 0; index < input.size(); index++) {
            double trendValue = trend.get(index);
            double percent = percentTrend.get(index);
            double cycleValue = trendValue * (1 + percent / 100);
            result.add(round(cycleValue));
        }
        for (int index = 0; index < cycleLength; index++) {
            double trendValue = trend.get(input.size() + index);
            double percent = percentTrend.get(input.size() - cycleLength + index);
            double cycleValue = trendValue * (1 + percent / 100);
            result.add(round(cycleValue));
        }
        return result;
    }
}
