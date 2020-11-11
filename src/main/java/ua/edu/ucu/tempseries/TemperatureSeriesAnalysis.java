package ua.edu.ucu.tempseries;

import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TemperatureSeriesAnalysis {

    public double[] temperatures;
    static final int minTemperature = -273;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature : temperatureSeries) {
            if (temperature < minTemperature) {
                throw new InputMismatchException("There is a value less than -273C");
            }
        }
        temperatures = copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double average() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException("The temperature array is empty");
        }
        else {
            double sumElements = 0;
            for(double element: temperatures) {
                sumElements += element;
            }
            return sumElements/temperatures.length;
        }
    }

    public double deviation() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException("The temperature array is empty");
        }
        else {
            double mean = average();
            double sumSqrtDiff = 0;
            for (double temperature : temperatures) {
                double newElement = temperature - mean;
                newElement *= newElement;
                sumSqrtDiff += newElement;
            }
            double meanSqrtDiff = sumSqrtDiff / temperatures.length;
            return sqrt(meanSqrtDiff);
        }
    }

    public double min() {
        if(temperatures.length == 0){
            throw new IllegalArgumentException("The temperature array is empty");
        }
        else {
            Arrays.sort(temperatures);
            return temperatures[0];
        }
    }

    public double max() {
        if(temperatures.length == 0) {
            throw new IllegalArgumentException("The temperature array is empty");
        }
        else {
            Arrays.sort(temperatures);
            return temperatures[temperatures.length-1];
        }
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        double minDifference = Double.POSITIVE_INFINITY;
        double elementWithMinDifference = 0;
        for(double temperature: temperatures) {
            double curDifference = abs(abs(temperature) - abs(tempValue));
            if(curDifference < minDifference) {
                minDifference = curDifference;
                elementWithMinDifference = temperature;
            }
            else if(curDifference == minDifference) {
                if(elementWithMinDifference < temperature) {
                    elementWithMinDifference = temperature;
                }
            }
        }
        return elementWithMinDifference;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] tempArray = new double[temperatures.length];
        int firstZeroIndex = 0;
        for (double temperature : temperatures) {
            if (temperature < tempValue) {
                tempArray[firstZeroIndex] = temperature;
                firstZeroIndex += 1;
            }
        }
        double[] result;
        result = copyOf(tempArray, firstZeroIndex);
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] tempArray = new double[temperatures.length];
        int firstZeroIndex = 0;
        for (double temperature : temperatures) {
            if (temperature >= tempValue) {
                tempArray[firstZeroIndex] = temperature;
                firstZeroIndex += 1;
            }
        }
        double[] result;
        result = copyOf(tempArray, firstZeroIndex);
        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        TempSummaryStatistics tempStatistics = new TempSummaryStatistics(average(), deviation(), min(), max());
        return tempStatistics;
    }

    public int addTemps(double... temps) {
        int sumResult = 0;

        for(double temperature:temperatures) {
            sumResult += temperature;
        }

        int previousLength = temperatures.length;
        int initialLength = temperatures.length;
        temperatures = copyOf(temperatures, previousLength*2);
        int i = 0;
        for(double element: temps) {
            try {
                if(element < minTemperature) {
                    temperatures = copyOf(temperatures, initialLength);
                    throw new InputMismatchException("There is a value less than 273");
                }
                temperatures[i + previousLength] = element;
                sumResult += element;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                previousLength = temperatures.length;
                temperatures = copyOf(temperatures, previousLength*2);
                temperatures[i + previousLength] = element;
                sumResult += element;
            }
        }
        return sumResult;
    }
}
