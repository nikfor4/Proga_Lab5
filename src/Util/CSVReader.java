package Util;

import java.io.*;

public class CSVReader implements Closeable {
    private final BufferedReader reader;

    public CSVReader(String filename) throws IOException {
        FileInputStream file = new FileInputStream(filename);
        InputStreamReader read = new InputStreamReader(file);
        this.reader = new BufferedReader(read);
    }

    public boolean hasNext() throws IOException {
        return reader.ready(); // Проверяет, есть ли следующая строка
    }

    public String[] readLine() throws IOException {
        String line = reader.readLine();
        return line == null ? null : line.split(",");
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
