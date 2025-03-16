package Commands;

import Util.RobotInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.Semaphore;

/**
 * Класс {@code ExecuteScript} представляет команду для выполнения скриптов из указанного файла.
 * Скрипт выполняет команды, прочитанные из файла, с блокировкой ввода пользователя.
 */
public class ExecuteScript implements Command {

    /** Блокировка для синхронизации ввода пользователя. */
    private static final Semaphore inputLock = new Semaphore(1);

    /** Стек, в котором хранится список файлов скриптов, чтобы избежать рекурсии. */
    private static Stack<String> inputStack = new Stack<>();

    /**
     * Запрашивает у пользователя путь к файлу скрипта.
     */
    @Override
    public void execute() {
        System.out.println("Укажите путь к файлу скрипта.");
    }

    /**
     * Выполняет команды из файла, указанного в аргументе.
     * Проверяет, существует ли файл, и не вызывает сам себя.
     * Запускает выполнение в отдельном потоке, блокируя ввод во время выполнения.
     *
     * @param args Параметры команды. Ожидается путь к файлу.
     */
    @Override
    public void execute(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Ошибка: путь к файлу не указан.");
            return;
        }

        String filePath = args[0];

        // Проверка, существует ли файл
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Ошибка: файл не найден по пути: " + filePath);
            return;
        }
        if (inputStack.contains(filePath)) {
            System.out.println("Скрипт не может вызывать сам себя");
            return;
        }
        // Попытка захватить блокировку
        try {
            inputLock.acquire(); // Блокируем ввод пользователя
            new Thread(() -> {
                try {
                    inputStack.push(filePath);
                    executeScript(filePath);
                    inputStack.pop();
                } finally {
                    inputLock.release(); // Освобождаем ввод после выполнения
                }
            }).start();

        } catch (InterruptedException e) {
            System.out.println("Ошибка при блокировке ввода: " + e.getMessage());
        }

    }

    /**
     * Читает строки из файла и выполняет каждую команду.
     * Выводит содержимое файла и запускает выполнение команд.
     *
     * @param filePath Путь к файлу, который будет обработан.
     */
    private void executeScript(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Чтение строк из файла и помещение их в массив
            String[] lines = reader.lines().toArray(String[]::new);
            System.out.println("Содержимое файла: " + Arrays.toString(lines));

            if (lines.length > 0) {
                for (String line : lines) {
                    robotInput(line);
                }
            } else {
                System.out.println("Файл пустой: " + filePath);
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    /**
     * Выполняет команду, используя {@code RobotInput}.
     *
     * @param line Строка, представляющая команду для выполнения.
     */
    private void robotInput(String line) {
        RobotInput.execute(line);
    }

    /**
     * Выводит информацию о команде {@code execute_script}.
     * Описание функционала команды.
     */
    @Override
    public void PrintInfo() {
        System.out.println("execute_script filepath — выполняет команды из указанного файла.");
    }

    /**
     * Получает блокировку ввода.
     *
     * @return Блокировка для синхронизации ввода пользователя.
     */
    public static Semaphore getInputLock() {
        return inputLock;
    }
}
