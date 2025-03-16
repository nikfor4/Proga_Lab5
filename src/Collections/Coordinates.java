package Collections;

/**
 * Класс {@code Coordinates} представляет собой координаты на плоскости с целочисленными значениями x и y.
 */
public class Coordinates {
    private final int x; // Координата X
    private final int y; // Координата Y

    /**
     * Создает объект {@code Coordinates} с заданными значениями x и y.
     *
     * @param x Координата по оси X.
     * @param y Координата по оси Y.
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получает значение координаты X.
     *
     * @return Значение X.
     */
    public int getX() {
        return x;
    }

    /**
     * Получает значение координаты Y.
     *
     * @return Значение Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Преобразует координаты в строку в формате "X;Y".
     *
     * @return Строковое представление координат.
     */
    @Override
    public String toString() {
        return x + ";" + y;
    }
}
