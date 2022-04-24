package com.progressoft.tools;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class NormalizerIml implements Normalizer {

    @Override

    public ScoringSummary zscore(Path csvPath, Path destPath, String colToStandardize) throws IOException {


        if (!Files.exists(csvPath)) { // to check if source file not found
            throw new IllegalArgumentException("source file not found");
        }


        BufferedReader RowFirstline = new BufferedReader(new FileReader(String.valueOf(csvPath))); // to read from source file  by put it in buffer
        String firstRow = RowFirstline.readLine(); // to read first line

        if (!firstRow.contains(colToStandardize)) { // to check if the column that we will Standardize in exist in file
            throw new IllegalArgumentException("column " + colToStandardize + " not found");
        }


        String[] cvsColumns = firstRow.split(","); // to find cell in row
        ArrayList<Integer> data = new ArrayList<>(); //  to put column that we will Standardize
        int indexOfColum = Arrays.asList(cvsColumns).indexOf(colToStandardize); //to determine to index of column that we will Standardize


        Path newFile = Files.createFile(destPath); // to crate file to write Standardize data on it  at dest Path
        FileWriter myWriter = new FileWriter(String.valueOf(newFile)); // to can write in file
        BufferedReader Row = new BufferedReader(new FileReader(String.valueOf(csvPath))); // to read all rows in file


        ///////////////////////////////// other way to chang the first line///////////////

       // String firstRowOfFile = Row.readLine(); //to read first Row in file witch the name of columns
//        myWriter.write(firstRow.substring(0, firstRowOfFile.indexOf(colToStandardize))
//                + colToStandardize + ',' + colToStandardize + "_z" +
//                firstRowOfFile.substring(firstRowOfFile.indexOf(colToStandardize) + colToStandardize.length()) + "\n");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////


        String line = ""; // row line

        while ((line = RowFirstline.readLine()) != null) {  // to convert row to array and put each cell ass array item
            String[] arrOfStr = line.split(",");
            data.add(Integer.valueOf(arrOfStr[indexOfColum]));
        }

        ScoringSummaryImp newOne = new ScoringSummaryImp(data);  // to crate instances
        int inxexOfRow = 0; // the index of row of colToStandardize column

        String stringToarray = ""; // var to store the line after convert it to String

        while ((line = Row.readLine()) != null) {

            String[] lineToArray = line.split(","); // to convent row to array
            List<String> lineAsList = new ArrayList<String>(); // to store the cell as list item
            Collections.addAll(lineAsList, lineToArray); // to convert from array to array list

            // duo to the first line just contain the name of columns
            if(inxexOfRow==0){
                lineAsList.add(indexOfColum+1, colToStandardize+"_z"); // add new  Standardized column
                myWriter.write(stringToarray.join(",", lineAsList) + "\n"); // add row to writer file
                inxexOfRow++; // to go on
            }

            else {
            lineAsList.add(indexOfColum+1, String.valueOf(newOne.Zscore(data.get(inxexOfRow++-1)))); // add  Standardized data in Standardized column
            myWriter.write(stringToarray.join(",", lineAsList) + "\n");} // add row to writer file


        }
        myWriter.close(); // close file

        return newOne;


    }




    @Override
    public ScoringSummary minMaxScaling(Path csvPath, Path destPath, String colToNormalize) throws IOException {

        if (!Files.exists(csvPath)) { // to check if source file not found
            throw new IllegalArgumentException("source file not found");
        }


        BufferedReader RowFirstline = new BufferedReader(new FileReader(String.valueOf(csvPath))); // to read from source file  by put it in buffer
        String firstRow = RowFirstline.readLine(); // to read first line

        if (!firstRow.contains(colToNormalize)) { // to check if the column that we will Normalize in exist in file
            throw new IllegalArgumentException("column " + colToNormalize + " not found");
        }


        String[] cvsColumns = firstRow.split(","); // to find cell in row
        ArrayList<Integer> data = new ArrayList<>(); //  to put column that we will Normalize
        int indexOfColum = Arrays.asList(cvsColumns).indexOf(colToNormalize); //to determine to index of column that we will Normalize


        Path newFile = Files.createFile(destPath); // to crate file to write Normalize data on it  at dest Path
        FileWriter myWriter = new FileWriter(String.valueOf(newFile)); // to can write in file
        BufferedReader Row = new BufferedReader(new FileReader(String.valueOf(csvPath))); // to read all rows in file


        ///////////////////////////////// other way to chang the first line///////////////////////////////////

        // String firstRowOfFile = Row.readLine(); //to read first Row in file witch the name of columns
//        myWriter.write(firstRow.substring(0, firstRowOfFile.indexOf(colToNormalize))
//                + colToStandardize + ',' + colToStandardize + "_mm" +
//                firstRowOfFile.substring(firstRowOfFile.indexOf(colToNormalize) + colToNormalize.length()) + "\n");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////


        String line = ""; // row line

        while ((line = RowFirstline.readLine()) != null) {  // to convert row to array and put each cell ass array item
            String[] arrOfStr = line.split(",");
            data.add(Integer.valueOf(arrOfStr[indexOfColum]));
        }

        ScoringSummaryImp newOne = new ScoringSummaryImp(data);  // to crate instances
        int inxexOfRow = 0; // the index of row of colToNormalize column

        String stringToarray = ""; // var to store the line after convert it to String

        while ((line = Row.readLine()) != null) {

            String[] lineToArray = line.split(","); // to convent row to array
            List<String> lineAsList = new ArrayList<String>(); // to store the cell as list item
            Collections.addAll(lineAsList, lineToArray); // to convert from array to array list

            // duo to the first line just contain the name of columns
            if(inxexOfRow==0){
                lineAsList.add(indexOfColum+1, colToNormalize+"_mm"); // add new  Normalize column
                myWriter.write(stringToarray.join(",", lineAsList) + "\n"); // add row to writer file
                inxexOfRow++; // to go on
            }

            else {
                lineAsList.add(indexOfColum+1, String.valueOf(newOne.minMaxScaling(data.get(inxexOfRow++-1)))); // add  Normalize data in Normalize column
                myWriter.write(stringToarray.join(",", lineAsList) + "\n");} // add row to writer file


        }
        myWriter.close(); // close file

        return newOne;


    }
}
