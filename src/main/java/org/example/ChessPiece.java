package org.example;

/**
 * шахматная фигура
 */
public abstract class ChessPiece {
    /**
     * Цвет фигуры
     */
    protected String color;
    /**
     * Логическая переменная (по умолчанию true)
     */
    protected boolean check = true;

    /**
     * Конструктор, принимающий цвет фигуры
     */
    public ChessPiece(String color) {
        this.color = color;
    }

    /**
     * Метод для получения цвета фигуры
     *
     * @return цвет
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Абстрактный метод для проверки возможности хода фигуры
     *
     * @param chessBoard Шахматная доска
     * @param line       строка
     * @param column     столбец
     * @param toLine     на строка
     * @param toColumn   на столбец
     * @return возможности хода фигуры
     */
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    /**
     * Абстрактный метод для получения символа фигуры
     *
     * @return символ фигуры
     */
    public abstract String getSymbol();
}
