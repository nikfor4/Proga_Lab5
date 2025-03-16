package Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.Semaphore;

public class ExecuteScript implements Command {

    private static final Semaphore inputLock = new Semaphore(1);

    private static Stack<String> inputStack = new Stack<>();

    @Override
    public void execute() {
        System.out.println("Укажите путь к файлу скрипта.");
    }

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

    private void robotInput(String line) {
        RobotInput.execute(line);
    }

    @Override
    public void PrintInfo() {
        System.out.println("Команда execute_script — выполняет команды из указанного файла.");
    }

    public static Semaphore getInputLock() {
        return inputLock;
    }
}
