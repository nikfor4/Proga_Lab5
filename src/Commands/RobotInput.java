package Commands;

import Util.QueueManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Spliterator;

public class RobotInput {
    public static void execute(String args) {
        QueueManager manager = new QueueManager(13);


        String command = String.valueOf(args);

        try {
            // Создаем объект Robot для симуляции ввода
            Robot robot = new Robot();
            // Делаем небольшую задержку перед началом ввода
            robot.delay(100);

            // Используем BufferedReader для построчного чтения файла
            // Чтение строк из файла и помещение их в массив
            for (int i = 0; i < command.length(); i++) {
                char c = command.charAt(i);
                pressKey(robot, c);  // Симулируем нажатие клавиш
                // Задержка между символами
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    // Метод для симуляции нажатия клавиши
    private static void pressKey(Robot robot, char c) {
        if (c == '_') {robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_SHIFT);}
        else {
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

        if (keyCode == KeyEvent.CHAR_UNDEFINED) {
            System.err.println("Неизвестный символ: " + c);
            return;
        }

        boolean shiftNeeded = Character.isUpperCase(c);

        if (shiftNeeded) {
            robot.keyPress(KeyEvent.VK_SHIFT);
        }

        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);

        if (shiftNeeded) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
    }}
}
