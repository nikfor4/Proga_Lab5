package Util;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class CSVWriter {
    public static void main(String[] args) {
        String filePath = "Data/Data.csv";
        String line = "";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
}}