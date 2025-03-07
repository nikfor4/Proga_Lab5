package Util;

import java.io.*;

public class CSVReader {
    private final BufferedReader reader;

    public CSVReader(String filename) throws IOException {
        FileInputStream file = new FileInputStream(filename);
        InputStreamReader read = new InputStreamReader(file);
        this.reader = new BufferedReader(read);
    }

    public String[] readLine() throws IOException {
        String line = reader.readLine();
        return line == null ? null : line.split(",");
    }
}