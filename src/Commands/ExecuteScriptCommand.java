package Commands;

import Util.RobotInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * Класс {@code ExecuteScriptCommand} реализует команду execute_script для выполнения команд из указанного скриптового файла.
 * Поддерживает вложенные вызовы скриптов и предотвращает рекурсивные вызовы одного и того же скрипта.
 * При выполнении скриптов пользовательский ввод блокируется.
 */
public class ExecuteScriptCommand implements Command {

    /** Блокировка для синхронизации ввода пользователя во время выполнения скриптов. */
    private static final Semaphore inputLock = new Semaphore(1);

    /** Стек для хранения контекста выполнения вложенных скриптов. */
    private static final Stack<ScriptContext> scriptStack = new Stack<>();

    /**
     * Внутренний класс, представляющий контекст выполнения конкретного скрипта.
     */
    private static class ScriptContext {
        /** Путь к файлу скрипта. */
        String filePath;
        /** Список строк (команд) скрипта. */
        List<String> lines;
        /** Индекс текущей обрабатываемой строки скрипта. */
        int currentLine;

        /**
         * Конструктор контекста скрипта.
         * @param filePath путь к файлу скрипта
         * @param lines список строк скрипта
         */
        ScriptContext(String filePath, List<String> lines) {
            this.filePath = filePath;
            this.lines = lines;
            this.currentLine = 0;
        }
    }

    /**
     * Запрашивает у пользователя путь к скрипту.
     */
    @Override
    public void execute() {
        System.out.println("Укажите путь к файлу скрипта.");
    }

    /**
     * Запускает выполнение скрипта из указанного файла с поддержкой вложенных скриптов.
     * Блокирует пользовательский ввод на время выполнения.
     *
     * @param args массив аргументов, где ожидается путь к файлу скрипта.
     */
    @Override
    public void execute(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Ошибка: путь к файлу не указан.");
            return;
        }

        String filePath = args[0];

        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Ошибка: файл не найден по пути: " + filePath);
            return;
        }

        if (scriptStack.stream().anyMatch(ctx -> ctx.filePath.equals(filePath))) {
            System.out.println("Ошибка: обнаружена рекурсия при вызове скрипта: " + filePath);
            return;
        }

        try {
            inputLock.acquire(); // Блокируем пользовательский ввод

            new Thread(() -> {
                try {
                    pushAndExecute(filePath);
                } finally {
                    if (scriptStack.isEmpty()) {
                        inputLock.release(); // Освобождаем ввод после завершения всех скриптов
                    }
                }
            }).start();

        } catch (InterruptedException e) {
            System.out.println("Ошибка при блокировке ввода: " + e.getMessage());
        }
    }

    /**
     * Загружает скрипт в стек и запускает его выполнение.
     *
     * @param filePath путь к файлу скрипта
     */
    private void pushAndExecute(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = reader.lines().toList();
            scriptStack.push(new ScriptContext(filePath, lines));
            processScript();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    /**
     * Обрабатывает команды скриптов в стеке, поддерживая вложенные вызовы.
     * После завершения вложенного скрипта возвращается к предыдущему.
     */
    private void processScript() {
        while (!scriptStack.isEmpty()) {
            ScriptContext ctx = scriptStack.peek();
            while (ctx.currentLine < ctx.lines.size()) {
                String line = ctx.lines.get(ctx.currentLine).trim();
                ctx.currentLine++;

                if (line.isEmpty()) continue;

                if (line.startsWith("execute_script")) {
                    String[] tokens = line.split("\\s+");
                    if (tokens.length > 1) {
                        String nestedPath = tokens[1];
                        if (scriptStack.stream().anyMatch(s -> s.filePath.equals(nestedPath))) {
                            System.out.println("\nОшибка: обнаружена рекурсия при вызове скрипта: " + nestedPath);
                            continue;
                        }
                        try (BufferedReader nestedReader = new BufferedReader(new FileReader(nestedPath))) {
                            List<String> nestedLines = nestedReader.lines().toList();
                            scriptStack.push(new ScriptContext(nestedPath, nestedLines));
                            break; // Переход к обработке вложенного скрипта
                        } catch (IOException e) {
                            System.out.println("\nОшибка чтения файла: " + e.getMessage());
                        }
                    } else {
                        System.out.println("\nОшибка: путь к скрипту не указан в строке: " + line);
                    }
                } else {
                    robotInput(line);
                }
            }

            if (ctx.currentLine >= ctx.lines.size()) {
                scriptStack.pop();
            }
        }
    }

    /**
     * Выполняет команду, переданную строкой, с помощью {@link RobotInput}.
     *
     * @param line строка-команда для выполнения
     */
    private void robotInput(String line) {
        RobotInput.execute(line);
    }

    /**
     * Выводит описание команды {@code execute_script}.
     */
    @Override
    public void PrintInfo() {
        System.out.println("execute_script filepath — выполняет команды из указанного файла с поддержкой вложенных вызовов.");
    }

    /**
     * Предоставляет доступ к блокировке пользовательского ввода.
     *
     * @return {@link Semaphore} для синхронизации ввода пользователя
     */
    public static Semaphore getInputLock() {
        return inputLock;
    }
}
