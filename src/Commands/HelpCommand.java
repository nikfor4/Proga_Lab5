package Commands;

import java.util.Map;

/**
 * Команда {@code HelpCommand} выводит список доступных команд с их описаниями.
 * При вызове команды будет отображен список всех команд, зарегистрированных в {@link CommandProcessor}.
 */
public class HelpCommand implements Command {

    private Map<String, Command> data = CommandProcessor.commands;

    /**
     * Выводит информацию о команде {@code help}.
     * Описание того, что делает команда.
     */
    @Override
    public void PrintInfo() {
        System.out.println("help - выводит все команды");
    }

    /**
     * Выводит список всех доступных команд и их описания.
     * Для каждой команды вызывается метод {@link Command#PrintInfo()} для вывода ее описания.
     */
    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : data.entrySet()) {
            data.get(entry.getKey()).PrintInfo();
        }
    }

    /**
     * Выводит сообщение о том, что команда {@code help} не принимает аргументы.
     * Этот метод вызывается, если команда выполняется с аргументами.
     *
     * @param args Аргументы команды (не используются в данной реализации).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Команда help вводится без аргументов");
    }
}
