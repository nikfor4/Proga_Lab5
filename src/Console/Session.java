package Console;

/**
 * Класс Session представляет собой сессию клиента,
 * которая управляет выполнением команд через клиентский ввод.
 */
public class Session {
    // Путь к файлу
    private static String filename;

    /**
     * Метод run запускает сессию, используя указанный путь к файлу.
     * Внутри создается объект клиента, который обрабатывает команды.
     * @param filename путь к файлу, который будет использоваться клиентом
     */
    public void run(String filename) {
        Client client = new Client();
        client.userInput(filename);
    }
}
