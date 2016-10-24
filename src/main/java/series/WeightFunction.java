package series;

@FunctionalInterface
public interface WeightFunction {
    double weight(int distance);
}
