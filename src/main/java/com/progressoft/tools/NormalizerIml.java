package com.progressoft.tools;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class NormalizerIml implements Normalizer {

    @Override
    public ScoringSummary zscore(Path csvPath, Path destPath, String colToStandardize) throws IOException {


        if (!Files.exists(csvPath)) {

            throw new IllegalArgumentException("source file not found");
        }
        BufferedReader Row = new BufferedReader(new FileReader(String.valueOf(csvPath)));
        String firstRow = Row.readLine();

        if (!firstRow.contains(colToStandardize)) {

            throw new IllegalArgumentException("column " + colToStandardize + " not found");

        }

        String[] cvsColumns = firstRow.split(",");

        ArrayList<Integer> data = new ArrayList<>();
        int indexOfColum = Arrays.asList(cvsColumns).indexOf(colToStandardize);

        Path newFile = Files.createFile(destPath);

        FileWriter myWriter = new FileWriter(String.valueOf(newFile));

        BufferedReader br1 = new BufferedReader(new FileReader(String.valueOf(csvPath)));

        String firstRowOfFile = br1.readLine();


        myWriter.write(firstRow.substring(0, firstRowOfFile.indexOf(colToStandardize)) + colToStandardize + ',' + colToStandardize + "_z" + firstRowOfFile.substring(firstRowOfFile.indexOf(colToStandardize) + colToStandardize.length()) + "\n");

        String line1 = "";

        String line = "";
        while ((line = Row.readLine()) != null) {


            String[] arrOfStr = line.split(",");

            data.add(Integer.valueOf(arrOfStr[indexOfColum]));
        }

        ScoringSummaryImp newOne = new ScoringSummaryImp(data);
        int inxexOfcolume = 0;
        String stringToarray = "";
        while ((line1 = br1.readLine()) != null) {

            String[] lineToArray = line1.split(",");
            List<String> lineAsList = new ArrayList<String>();
            Collections.addAll(lineAsList, lineToArray);
            lineAsList.add(indexOfColum + 1, String.valueOf(newOne.Zscore(data.get(inxexOfcolume++))));
            myWriter.write(stringToarray.join(",", lineAsList) + "\n");


        }
        myWriter.close();

        return newOne;


    }


    @Override
    public ScoringSummary minMaxScaling(Path csvPath, Path destPath, String colToNormalize) throws IOException {
        if (!Files.exists(csvPath)) {

            throw new IllegalArgumentException("source file not found");
        }

        BufferedReader Row = new BufferedReader(new FileReader(String.valueOf(csvPath)));
        String firstRow = Row.readLine();
        if (!firstRow.contains(colToNormalize)) {

            throw new IllegalArgumentException("column " + colToNormalize + " not found");

        }

        String[] cvsColumns = firstRow.split(",");

        ArrayList<Integer> data = new ArrayList<>();
        int indexOfColum = Arrays.asList(cvsColumns).indexOf(colToNormalize);

        Path newFile = Files.createFile(destPath);

        FileWriter myWriter = new FileWriter(String.valueOf(newFile));

        BufferedReader br1 = new BufferedReader(new FileReader(String.valueOf(csvPath)));

        String firstRowOfFile = br1.readLine();


        myWriter.write(firstRow.substring(0, firstRowOfFile.indexOf(colToNormalize)) + colToNormalize + ',' + colToNormalize + "_mm" + firstRowOfFile.substring(firstRowOfFile.indexOf(colToNormalize) + colToNormalize.length()) + "\n");

        String line1 = "";

        String line = "";
        while ((line = Row.readLine()) != null) {


            String[] arrOfStr = line.split(",");

            data.add(Integer.valueOf(arrOfStr[indexOfColum]));
        }

        ScoringSummaryImp newOne = new ScoringSummaryImp(data);
        int inxexOfcolume = 0;
        String stringToarray = "";
        while ((line1 = br1.readLine()) != null) {

            String[] lineToArray = line1.split(",");
            List<String> lineAsList = new ArrayList<String>();
            Collections.addAll(lineAsList, lineToArray);
            lineAsList.add(indexOfColum + 1, String.valueOf(newOne.minMaxScaling(data.get(inxexOfcolume++))));
            myWriter.write(stringToarray.join(",", lineAsList) + "\n");


        }
        myWriter.close();

        return newOne;



    }
}
