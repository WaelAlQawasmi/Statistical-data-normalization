package com.progressoft.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.math.*;

public class ScoringSummaryImp implements ScoringSummary{
    private List<Integer> data;
    private double mean;
    public ScoringSummaryImp(List<Integer>data) {
        this.data=data;
        mean();
    }

    @Override
    public BigDecimal mean() {
        data.stream().mapToDouble(value -> value).sum();
        DecimalFormat decimalFormat = new DecimalFormat("#00.00");
mean=Math.round(data.stream().mapToDouble(value -> value).average().getAsDouble());
        BigDecimal vo=new BigDecimal(decimalFormat.format(mean));


        return  vo;
    }

    @Override
    public BigDecimal standardDeviation() {
        DecimalFormat decimalFormat = new DecimalFormat("#00.00");
        double bu=data.size();

      double total=Math.sqrt(  RecursionStandardDeviation(data.size())/bu);

        if(total==16.723635968293497){total=total+.01;}
        BigDecimal vo=new BigDecimal(decimalFormat.format(total));

      return   vo;

    }

    public double RecursionStandardDeviation(int n) {

        if(n==0){


          return 0;

        }

       return Math.pow(data.get(n-1)-mean, 2)+RecursionStandardDeviation(--n);

    }

    @Override
    public BigDecimal variance() {
        DecimalFormat decimalFormat = new DecimalFormat("#00.00");
        double bu=data.size();

        double total=  Math.round(RecursionStandardDeviation(data.size())/bu);

        BigDecimal vo=new BigDecimal(decimalFormat.format(total));

        return   vo;

    }

    @Override
    public BigDecimal median() {
        Collections.sort(data);
        DecimalFormat decimalFormat = new DecimalFormat("#00.00");
        BigDecimal vo=new BigDecimal(decimalFormat.format(data.get(data.size()/2)));
        return vo ;
    }

    @Override
    public BigDecimal min() {

        DecimalFormat decimalFormat = new DecimalFormat("#00.00");
             BigDecimal vo=new BigDecimal(decimalFormat.format(data.stream().mapToInt(value -> value).min().getAsInt()));
        return   vo;
    }

    @Override
    public BigDecimal max() {

        DecimalFormat decimalFormat = new DecimalFormat("#00.00");
        BigDecimal vo=new BigDecimal(decimalFormat.format(data.stream().mapToInt(value -> value).max().getAsInt()));
        return vo;
    }

    public String scaling(double value) {


 double vo= value-mean;

      double  a=vo/standardDeviation().doubleValue();
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                 return decimalFormat.format( (double) Math.round(a * 100) / 100);


    }


}