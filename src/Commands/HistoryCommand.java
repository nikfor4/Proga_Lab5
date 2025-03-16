package Commands;

import Util.QueueManager;

/**
 * Команда {@code HistoryCommand} выводит последние 13 команд из истории команд, хранящейся в {@link QueueManager}.
 * История команд позволяет отслеживать предыдущие действия пользователя в приложении.
 */
public class HistoryCommand implements Command {

    private final QueueManager manager;

    /**
     * Конструктор команды {@code HistoryCommand}.
     * Инициализирует {@link QueueManager}, который используется для получения истории команд.
     *
     * @param manager Менеджер, управляющий историей команд.
     */
    public HistoryCommand(QueueManager manager) {
        this.manager = manager;
    }

    /**
     * Выводит 13 последних команд из истории.
     * История команд доступна через {@link QueueManager#getQueue()}.
     * В случае если команд меньше 13, будет выведено количество доступных команд.
     */
    @Override
    public void execute() {
        System.out.println("История команд: " + manager.getQueue());
    }

    /**
     * Выводит сообщение о том, что команда {@code history} не принимает аргументы.
     * Этот метод вызывается, если команда выполняется с аргументами.
     *
     * @param args Аргументы команды (не используются в данной реализации).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Команда history вводится без аргументов");
    }

    /**
     * Выводит информацию о команде {@code history}.
     * Описание того, что делает команда.
     */
    @Override
    public void PrintInfo() {
        System.out.println("history - показывает 13 последних команд");
    }
}
