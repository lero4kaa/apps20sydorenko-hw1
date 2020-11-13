package ua.edu.ucu.tempseries;


public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;
    private final double ALLOWEDDIFF = 0.0001;

    public TempSummaryStatistics(double avgTemp, double devTemp,
                                 double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public boolean equal(TempSummaryStatistics other) {
        return other.avgTemp == this.avgTemp
                && Math.abs(other.devTemp - this.devTemp) < ALLOWEDDIFF
                && other.minTemp == this.minTemp
                && other.maxTemp == this.maxTemp;
    }
}
