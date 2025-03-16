package Util;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Класс RobotInput используется для симуляции ввода текста с помощью робота.
 * Он позволяет передавать текст в виде строки и симулировать нажатие клавиш на клавиатуре.
 */
public class RobotInput {

    /**
     * Выполняет симуляцию ввода текста с помощью объекта Robot.
     * Каждая буква из строки передается роботу, а затем симулируется её нажатие.
     * В конце строки имитируется нажатие клавиши Enter.
     *
     * @param args строка текста, который нужно ввести
     */
    public static void execute(String args) {
        QueueManager manager = new QueueManager(13); // Очередь команд (история) для дальнейшего использования

        String command = String.valueOf(args); // Преобразуем переданные аргументы в строку

        try {
            // Создаем объект Robot для симуляции ввода
            Robot robot = new Robot();
            // Делаем небольшую задержку перед началом ввода
            robot.delay(100);

            // Чтение строк из команды и симуляция ввода
            for (int i = 0; i < command.length(); i++) {
                char c = command.charAt(i);
                pressKey(robot, c);  // Симулируем нажатие клавиш
            }
            // Симуляция нажатия клавиши Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для симуляции нажатия клавиши.
     * Обрабатывает символы, включая заглавные буквы и специальные символы.
     *
     * @param robot объект Robot, который будет симулировать нажатие клавиш
     * @param c символ, который нужно ввести
     */
    private static void pressKey(Robot robot, char c) {
        // Обработка символа "_" для использования клавиши Shift + Minus
        if (c == '_') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else {
            // Получаем код клавиши для символа
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

            if (keyCode == KeyEvent.CHAR_UNDEFINED) {
                System.err.println("Неизвестный символ: " + c);
                return;
            }

            // Проверка, нужно ли нажимать Shift для символа
            boolean shiftNeeded = Character.isUpperCase(c);

            if (shiftNeeded) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }

            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);

            if (shiftNeeded) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }
}
