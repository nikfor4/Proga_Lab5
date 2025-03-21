package Commands;

/**
 * Команда {@code ExitCommand} завершает выполнение программы.
 * При вызове команды выводится сообщение в виде ASCII-арта, после чего программа завершает свою работу.
 */
public class ExitCommand implements Command {

    /**
     * Завершает выполнение программы.
     */
    @Override
    public void execute() {
        System.out.println(
                "██████╗░██╗░░░██╗███████╗\n" +
                        "██╔══██╗╚██╗░██╔╝██╔════╝\n" +
                        "██████╦╝░╚████╔╝░█████╗░░\n" +
                        "██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
                        "██████╦╝░░░██║░░░███████╗\n" +
                        "╚═════╝░░░░╚═╝░░░╚══════╝");
        System.exit(0);
    }

    /**
     * Печатает сообщение о том, что команда {@code exit} не принимает аргументов.
     * Этот метод вызывается, если команда выполняется с аргументами.
     *
     * @param args Аргументы команды (не используются в данной реализации).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Команда exit вводится без аргументов");
    }

    /**
     * Выводит информацию о команде {@code exit}.
     * Описание функционала команды.
     */
    @Override
    public void PrintInfo() {
        System.out.println("exit - выход из приложения без сохранения");
    }
}
