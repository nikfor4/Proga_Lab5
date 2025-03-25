package Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExecuteScriptCommand implements Command {

    private CommandProcessor commandProcessor;

    // Очередь путей для предотвращения рекурсии
    private static List<String> executingScripts = new ArrayList<>();

    public ExecuteScriptCommand(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Override
    public void execute(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Ошибка: путь к файлу не указан.");
            return;
        }

        String filePath = args[0]; // Путь к файлу передается в аргументах

        // Проверка существования файла
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Ошибка: файл не найден по пути: " + filePath);
            return;
        }

        // Проверка на доступность чтения файла
        if (!Files.isReadable(Paths.get(filePath))) {
            System.out.println("Ошибка: нет доступа для чтения файла: " + filePath);
            return;
        }

        // Проверка на рекурсию, если скрипт уже выполняется
        if (executingScripts.contains(filePath)) {
            System.out.println("Ошибка: рекурсия скриптов. Скрипт " + filePath + " уже выполняется.");
            return;
        }

        // Добавляем текущий путь в очередь выполнения
        executingScripts.add(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Пропуск пустых строк
                if (line.isEmpty()) {
                    continue;
                }

                // Обработка команд с аргументами
                if (line.startsWith("add")) {
                    String[] addArgs = new String[8];
                    for (int i = 0; i < 8; i++) {
                        if ((line = reader.readLine()) != null) {
                            addArgs[i] = line.trim();
                        }
                    }
                    Command addCommand = commandProcessor.getCommands().get("add");
                    if (addCommand != null) {
                        addCommand.execute(addArgs); // Выполняем команду add с аргументами
                    }
                } else if (line.startsWith("update")) {
                    String[] updateArgs = new String[9];
                    for (int i = 0; i < 9; i++) {
                        if ((line = reader.readLine()) != null) {
                            updateArgs[i] = line.trim();
                        }
                    }
                    Command updateCommand = commandProcessor.getCommands().get("update");
                    if (updateCommand != null) {
                        updateCommand.execute(updateArgs); // Выполняем команду update с аргументами
                    }
                } else {
                    if (line.split(" ").length == 1) {
                        Command command = commandProcessor.getCommands().get(line);
                        if (command != null) {
                            command.execute(); // Выполняем команду с аргументами
                        } else {
                            System.out.println("Команда не найдена: " + line);
                        }
                    }
                    else {
                        String[] parts = line.split(" ", 2);
                        String commandName = parts[0];
                        String argument = parts.length > 1 ? parts[1].trim() : null;

                        Command command = commandProcessor.getCommands().get(commandName);
                        if (command != null) {
                            if (argument != null) {
                                command.execute(new String[]{argument}); // Выполняем команду с аргументом
                            } else {
                                command.execute(); // Выполняем команду без аргументов, если аргумент отсутствует
                            }
                        } else {
                            System.out.println("Команда не найдена: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } finally {
            // Удаляем путь из очереди после выполнения скрипта
            executingScripts.remove(filePath);
        }
    }

    @Override
    public void execute() {
        // Пустая реализация, так как для скрипта используется execute(String[] args)
    }

    @Override
    public void PrintInfo() {
        System.out.println("execute_script filepath — выполняет команды из указанного файла.");
    }
}
