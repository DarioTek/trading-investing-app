package com.dariotek.util;

import java.io.FileWriter;
import java.util.Arrays;

public class CVSUtilExample {

    public static void main(String[] args) throws Exception {

        String csvFile = "C:\\Users\\dmont\\Documents\\My Project\\test.csv";
        FileWriter writer = new FileWriter(csvFile);

        CSVWriterUtility.writeLine(writer, Arrays.asList("a", "b", "c", "d"));

        //custom separator + quote
        CSVWriterUtility.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), ',', '"');

        //custom separator + quote
        CSVWriterUtility.writeLine(writer, Arrays.asList("aaa", "bbb", "cc,c"), '|', '\'');

        //double-quotes
        CSVWriterUtility.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"));


        writer.flush();
        writer.close();

    }

}