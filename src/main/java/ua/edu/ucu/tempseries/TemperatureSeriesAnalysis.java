package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;
import java.util.Arrays;


public class TemperatureSeriesAnalysis {

    private double[] temperatures;
    private final int MINTEMPERATURE = -273;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature : temperatureSeries) {
            if (temperature < MINTEMPERATURE) {
                throw new InputMismatchException("There is a value "
                                                 + "less than -273C");
            }
        }
        temperatures = Arrays.copyOf(temperatureSeries,
                                     temperatureSeries.length);
    }

    public double average() {
        isEmpty();
        double sumElements = 0;
        for (double element: temperatures) {
            sumElements += element;
        }
        return sumElements/temperatures.length;
    }

    public double deviation() {
        isEmpty();
        double mean = average();
        double sumSqrtDiff = 0;
        for (double temperature : temperatures) {
            double newElement = temperature - mean;
            newElement *= newElement;
            sumSqrtDiff += newElement;
        }
        double meanSqrtDiff = sumSqrtDiff / temperatures.length;
        return Math.sqrt(meanSqrtDiff);
    }

    public double min() {
        isEmpty();
        Arrays.sort(temperatures);
        return temperatures[0];
    }

    public double max() {
        isEmpty();
        Arrays.sort(temperatures);
        return temperatures[temperatures.length-1];
    }

    public double findTempClosestToZero() {
        isEmpty();
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        isEmpty();
        double minDifference = Double.POSITIVE_INFINITY;
        double elementWithMinDifference = 0;
        for (double temperature: temperatures) {
            double curDifference = Math.abs(Math.abs(temperature)
                                            - Math.abs(tempValue));
            if (curDifference < minDifference) {
                minDifference = curDifference;
                elementWithMinDifference = temperature;
            }
            else if (curDifference == minDifference) {
                if (elementWithMinDifference < temperature) {
                    elementWithMinDifference = temperature;
                }
            }
        }
        return elementWithMinDifference;
    }

    public double[] findTempsLessThen(double tempValue) {
        isEmpty();
        double[] tempArray = new double[temperatures.length];
        int firstZeroIndex = 0;
        for (double temperature : temperatures) {
            if (temperature < tempValue) {
                tempArray[firstZeroIndex] = temperature;
                firstZeroIndex += 1;
            }
        }
        double[] result;
        result = Arrays.copyOf(tempArray, firstZeroIndex);
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        isEmpty();
        double[] tempArray = new double[temperatures.length];
        int firstZeroIndex = 0;
        for (double temperature : temperatures) {
            if (temperature >= tempValue) {
                tempArray[firstZeroIndex] = temperature;
                firstZeroIndex += 1;
            }
        }
        double[] result;
        result = Arrays.copyOf(tempArray, firstZeroIndex);
        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        isEmpty();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        int sumResult = 0;

        if (temperatures.length == 0) {
            temperatures = Arrays.copyOf(temperatures, 1);
            temperatures[0] =  temps[0];
            sumResult += temperatures[0];
            temps = Arrays.copyOfRange(temps, 1, temps.length);
        }
        else {
            for (double temperature : temperatures) {
                sumResult += temperature;
            }
        }

        int previousLength = temperatures.length;
        int initialLength = temperatures.length;
        temperatures = Arrays.copyOf(temperatures, previousLength*2);

        int i = 0;

        for (double element: temps) {
            try {
                if (element < MINTEMPERATURE) {
                    temperatures = Arrays.copyOf(temperatures, initialLength);
                    throw new InputMismatchException("There is a value "
                                                     + "less than 273");
                }
                temperatures[i + previousLength] = element;
                sumResult += element;
                i++;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                previousLength = temperatures.length;
                temperatures = Arrays.copyOf(temperatures, previousLength*2);
                temperatures[i + previousLength] = element;
                sumResult += element;
            }
        }
        return sumResult;
    }

    public void isEmpty() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException("The array is empty.");
        }
    }
}
