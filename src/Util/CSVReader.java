package Util;

import java.io.*;

/**
 * Класс CSVReader используется для чтения данных из CSV файла.
 * Он предоставляет методы для построчного чтения данных и разбиения строк на части.
 */
public class CSVReader implements Closeable {
    private final BufferedReader reader;

    /**
     * Конструктор CSVReader создает объект для чтения из указанного файла.
     * @param filename путь к CSV файлу, из которого нужно читать данные
     * @throws IOException если файл не найден или не удается открыть файл для чтения
     */
    public CSVReader(String filename) throws IOException {
        FileInputStream file = new FileInputStream(filename);
        InputStreamReader read = new InputStreamReader(file);
        this.reader = new BufferedReader(read);
    }

    /**
     * Проверяет, есть ли еще строки для чтения из файла.
     * @return true, если есть следующая строка, иначе false
     * @throws IOException если возникает ошибка при чтении файла
     */
    public boolean hasNext() throws IOException {
        return reader.ready(); // Проверяет, есть ли следующая строка
    }

    /**
     * Читает следующую строку из CSV файла и возвращает ее как массив строк.
     * Каждая строка будет разделена по запятой.
     * @return массив строк, представляющий данные строки, или null, если достигнут конец файла
     * @throws IOException если возникает ошибка при чтении файла
     */
    public String[] readLine() throws IOException {
        String line = reader.readLine();
        return line == null ? null : line.split(",");
    }

    /**
     * Закрывает BufferedReader и освобождает ресурсы.
     * @throws IOException если возникает ошибка при закрытии ресурса
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }
}
