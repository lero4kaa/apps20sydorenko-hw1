package ua.edu.ucu.tempseries;

import static java.lang.Math.abs;

public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(double avgTemp, double devTemp,
                                 double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public boolean equal(TempSummaryStatistics other) {
        return other.avgTemp == this.avgTemp
                && abs(other.devTemp - this.devTemp) < 0.0001
                && other.minTemp == this.minTemp
                && other.maxTemp == this.maxTemp;
    }
}
