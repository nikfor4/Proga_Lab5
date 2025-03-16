package Validation;

import Collections.EventType;
import Collections.TicketType;

import java.util.Scanner;

public class InputValidate {
    /**
     * Запрашивает ввод данных у пользователя, проверяя их на корректность.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt Сообщение для запроса ввода.
     * @return Корректное введенное значение.
     */
    public static String getInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Ошибка: ввод не может быть пустым. Попробуйте снова.");
        }
        return null;
    }
    /**
     * Запрашивает ввод целочисленного значения у пользователя с проверкой корректности.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt Сообщение для запроса ввода.
     * @return Корректное целочисленное значение.
     */
    public static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное целочисленное значение.");
            }
        }
        return Integer.MIN_VALUE;
    }
    /**
     * Запрашивает ввод целочисленного значения у пользователя с проверкой корректности.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt Сообщение для запроса ввода.
     * @return Корректное целочисленное значение.
     */
    public static int getIntInputPlus(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            try {
                if (Integer.parseInt(input) > 0){
                    return Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное положительное целочисленное значение.");
            }
        }
        return Integer.MIN_VALUE;
    }
    /**
     * Запрашивает ввод целочисленного значения у пользователя с проверкой корректности.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt Сообщение для запроса ввода.
     * @return Корректное целочисленное значение.
     */
    public static float getFloatInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            try {
                if (Float.parseFloat(input) > 0) {
                    return Float.parseFloat(input);
                }
            } catch (NumberFormatException e) {
                break;
            }
        }
        return Float.MIN_VALUE;
    }

    /**
     * Запрашивает ввод целочисленного значения у пользователя с проверкой корректности.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param input input для считывания ввода пользователя.
     * @return Корректное целочисленное значение.
     */
    public static float getFloatInput(String input) {
        while (true) {
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            try {
                if (Float.parseFloat(input) > 0) {
                    return Float.parseFloat(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное целочисленное значение.");
            }
        }
        return Float.MIN_VALUE;
    }

    /**
     * Запрашивает ввод типа билета и проверяет корректность ввода.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt  Сообщение для запроса ввода.
     * @return Корректное значение TicketType.
     */
    public static TicketType getValidTicketType(Scanner scanner, String prompt) {
        TicketType ticketType = null;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equalsIgnoreCase("quit")) {
                break; // Выходим из цикла, но метод продолжает выполняться
            }

            try {
                ticketType = TicketType.valueOf(input);
                break; // Ввод корректный, выходим из цикла
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: допустимые значения - VIP, USUAL, BUDGETARY. Попробуйте снова.");
            }
        }
        return ticketType; // Возвращаем либо корректное значение, либо null
    }

    public static TicketType getValidTicketTypeInput(String scanner) {
        TicketType ticketType = null;
        while (true) {
            String input =scanner;

            if (input.equalsIgnoreCase("quit")) {
                break; // Выходим из цикла, но метод продолжает выполняться
            }

            try {
                ticketType = TicketType.valueOf(input);
                break; // Ввод корректный, выходим из цикла
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: допустимые значения - VIP, USUAL, BUDGETARY. Попробуйте снова.");
                return null;
            }
        }
        return ticketType; // Возвращаем либо корректное значение, либо null
    }

    /**
     * Запрашивает ввод типа события и проверяет корректность ввода.
     * Позволяет пользователю выйти из команды, введя "quit".
     *
     * @param scanner Scanner для считывания ввода пользователя.
     * @param prompt  Сообщение для запроса ввода.
     * @return Корректное значение EventType.
     */
    public static EventType getValidEventType(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            try {
                return EventType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: допустимые значения - CONCERT, FOOTBALL, BASKETBALL, OPERA, EXPOSITION. Попробуйте снова.");
            }
        }
        return null;
    }
}
