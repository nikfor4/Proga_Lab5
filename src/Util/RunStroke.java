package Util;

import java.util.Random;

/**
 * Класс {@code RunStroke} реализует имитацию прогресс-бара в консоли,
 * который заполняется с различными случайными задержками.
 *
 * <p>Прогресс-бар заполняется символами {@code #} от 0% до 100% с шагом в 1 позицию,
 * визуализируя выполнение некоторого процесса.</p>
 *
 * <p>Задержка между шагами прогресс-бара варьируется случайным образом
 * от 10 до 100 миллисекунд.</p>
 *
 */
public class RunStroke {
    /**
     * Точка входа в приложение.
     * <p>Создаёт и запускает прогресс-бар длиной 35 символов с имитацией случайных задержек.</p>
     *
     * @throws InterruptedException если поток был прерван во время выполнения {@code Thread.sleep()}.
     */
    public static void main() throws InterruptedException {
        int totalLength = 35;
        int progress = 0;
        float progressPercentage = 0;
        Random random = new Random();

        // Печать прогресс-бара со случайными задержками
        while (progress <= totalLength) {
            StringBuilder progressBar = new StringBuilder("|");
            for (int i = 0; i < progress; i++) {
                progressBar.append("#");
            }

            for (int i = progress; i < totalLength; i++) {
                progressBar.append(" ");
            }
            progressPercentage = (float) progress / (float) totalLength * 100;
            String formatedProgressPercentage = String.format("%.1f", progressPercentage);

            progressBar.append("|");
            System.out.print("\r" + progressBar.toString() + formatedProgressPercentage + "%");

            progress++;
            int delay = random.nextInt(91) + 10; // Задержка от 10 до 100 мс
            Thread.sleep(delay);
        }

        System.out.println();
    }
}
