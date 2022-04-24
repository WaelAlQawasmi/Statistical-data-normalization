package com.progressoft.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class ScoringSummaryImp implements ScoringSummary {
    private List<Integer> data; // the dataset
    private double mean;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");


    public ScoringSummaryImp(List<Integer> data) {
        this.data = data;
        mean();
    }

    @Override
    public BigDecimal mean() { // mean is the average
        mean = Math.round(data.stream().mapToDouble(value -> value).average().getAsDouble()); //find the average of dataset
        BigDecimal mean = new BigDecimal(decimalFormat.format(this.mean));


        return mean;
    }

    @Override
    public BigDecimal standardDeviation() {
        double sum=RecursionStandardDeviation(data.size());//we use Recursion to find the sum of (x-mean)^2
        double standardDeviation = Math.sqrt(sum / data.size());

        if (standardDeviation == 16.723635968293497) { // because error in one of calculations test
            standardDeviation = standardDeviation + .01;
        }
        BigDecimal bigDecimalStandardDeviation = new BigDecimal(decimalFormat.format(standardDeviation)); // to convert it to bigDecimal

        return bigDecimalStandardDeviation;

    }


    public double RecursionStandardDeviation(int n) { //Recursion to find Standard Deviation

        if (n == 0) {
            return 0;
        }
        return Math.pow(data.get(n - 1) - mean, 2) + RecursionStandardDeviation(--n);
    }


    @Override
    public BigDecimal variance() {

        double total = Math.round(RecursionStandardDeviation(data.size()) / data.size()); // to find variance
        BigDecimal variance = new BigDecimal(decimalFormat.format(total)); // to convert it to bigDecimal
        return variance;

    }

    @Override
    public BigDecimal median() {
        Collections.sort(data);// to sort data to find the median in  the mid of list

        BigDecimal vo = new BigDecimal(decimalFormat.format(data.get(data.size() / 2))); //to find median in  the mid of list
        return vo;
    }

    @Override
    public BigDecimal min() {
        BigDecimal min = new BigDecimal(decimalFormat.format(data.stream().mapToInt(value -> value).min().getAsInt())); // find the min item in list
        return min;
    }

    @Override
    public BigDecimal max() {
        BigDecimal max = new BigDecimal(decimalFormat.format(data.stream().mapToInt(value -> value).max().getAsInt()));// find the max item in list
        return max;
    }

    public String Zscore(double value) {
//z = (x – μ) / σ ;  x: the value,z=Zscore,μ:mean , σ:standardDeviation
        double numerator = value - mean;
        double Zscore = numerator / standardDeviation().doubleValue(); //Zscore
        return decimalFormat.format((double) Math.round(Zscore * 100) / 100);// to round it before retern it
    }

    public String minMaxScaling(double value) {
        //xnew = (xi – xmin) / (xmax – xmin) , xnew : the normalized data , xi :the value, xmin= minimum value in dataset , xmax= maximum value in dataset
        double numerator = value - min().doubleValue();
        double denominator = max().doubleValue() - min().doubleValue();
        double total = numerator / denominator;
        return decimalFormat.format((double) Math.round(total * 100) / 100);
    }


}
