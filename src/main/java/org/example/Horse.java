package org.example;

public class Horse extends ChessPiece{
    /**
     * Конструктор принимает цвет фигуры
     * @param color
     */
    public Horse(String color) {
        super(color);
    }

    /**
     * Метод getColor возвращает цвет фигуры
     * @return
     */
    @Override
    public String getColor() {
        return this.color;
    }

    /**
     * Метод canMoveToPosition проверяет, может ли конь ходить в заданную позицию
     * @param chessBoard Шахматная доска
     * @param line строка
     * @param column толбец
     * @param toLine
     * @param toColumn
     * @return
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся на доске
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        // Конь не может оставаться на том же месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем ход буквой "Г"
        int deltaLine = Math.abs(line - toLine);
        int deltaColumn = Math.abs(column - toColumn);
        if ((deltaLine == 2 && deltaColumn == 1) || (deltaLine == 1 && deltaColumn == 2)) {
            return true;
        }

        return false;
    }

    /**
     * Метод возвращает символ фигуры
     * @return
     */
    @Override
    public String getSymbol() {
        return "H"; // Символ коня
    }

    /**
     * Вспомогательный метод для проверки, что позиция находится на доске
     * @param line
     * @param column
     * @return
     */
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
