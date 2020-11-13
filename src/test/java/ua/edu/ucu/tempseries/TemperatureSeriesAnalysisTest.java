package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    double[] temperatureOneSeries = {-1.0};
    TemperatureSeriesAnalysis seriesOneAnalysis = new TemperatureSeriesAnalysis(temperatureOneSeries);

    double[] temperatureSeries1 = {3.0, -5.0, 1.0, 5.0};
    TemperatureSeriesAnalysis seriesAnalysis1 = new TemperatureSeriesAnalysis(temperatureSeries1);

    double[] temperatureSeries2 = {3.0, -5.0, -1.0, 1.0, 5.0};
    TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);

    double[] temperatureSeriesEmpty = {};
    TemperatureSeriesAnalysis seriesEmptyAnalysis = new TemperatureSeriesAnalysis(temperatureSeriesEmpty);

    @Test
    public void testAverageWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesOneAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        seriesEmptyAnalysis.average();
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;

        double actualResult = seriesAnalysis1.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        seriesEmptyAnalysis.deviation();
    }

    @Test
    public void testDeviation() {
        double expResult = 3.7416573867739;

        double actualResult = seriesAnalysis1.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        seriesEmptyAnalysis.min();
    }

    @Test
    public void testMin() {
        double expResult = -5.0;

        double actualResult = seriesAnalysis1.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        seriesEmptyAnalysis.max();
    }

    @Test
    public void testMax() {
        double expResult = 5.0;

        double actualResult = seriesAnalysis1.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {
        seriesEmptyAnalysis.findTempClosestToValue (7);
    }

    @Test
    public void testFindTempClosestToValue() {
        double expResult = 3.0;

        double actualResult = seriesAnalysis1.findTempClosestToValue(2.5);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {
        seriesEmptyAnalysis.findTempClosestToZero ();
    }

    @Test
    public void testFindTempClosestToZero() {
        double expResult = 1.0;

        double actualResult = seriesAnalysis2.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThenWithEmptyArray() {
        seriesEmptyAnalysis.findTempsLessThen (7.0);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] expResult = {-5.0, -1.0};

        double[] actualResult = seriesAnalysis2.findTempsLessThen(1.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThenWithEmptyArray() {
        seriesEmptyAnalysis.findTempsGreaterThen (7.0);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] expResult = {3.0, 1.0, 5.0};

        double[] actualResult = seriesAnalysis2.findTempsGreaterThen(1.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTempsWithSmallValue() {
        double expResult = 103.0;
        TemperatureSeriesAnalysis seriesAnalysis3 = new TemperatureSeriesAnalysis();

        int actualResult = seriesAnalysis3.addTemps(1.0, -5, 2.0, 3.0, 0.0, 2.0, 100.0, 0.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsWithSmallValueException() {
        int actualResult = seriesAnalysis2.addTemps(1.0, -500.00, 2.0, 3.0, 0.0, -274);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsEmpty() {
        TempSummaryStatistics statistics = seriesEmptyAnalysis.summaryStatistics();
    }

    @Test
    public void testSummaryStatistics() {
        TempSummaryStatistics statistics = seriesAnalysis1.summaryStatistics();
        TempSummaryStatistics expectedStatistics =
                new TempSummaryStatistics(1.0, 3.7416573867739, -5,5);
        assertEquals(true, statistics.equal(expectedStatistics));
    }

    @Test(expected = InputMismatchException.class)
    public void checkException() {
        double[] temperatureSeriesException = {5.0, -500.0};
        TemperatureSeriesAnalysis seriesAnalysisException =
                new TemperatureSeriesAnalysis(temperatureSeriesException);
    }
}
