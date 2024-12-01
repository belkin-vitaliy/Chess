package org.example;

/**
 * Конь
 */
public class Horse extends ChessPiece{
    /**
     * Конструктор принимает цвет фигуры
     * @param color цвет фигуры
     */
    public Horse(String color) {
        super(color);
    }

    /**
     * Метод getColor возвращает цвет фигуры
     * @return цвет фигуры
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
     * @param toLine на строку
     * @param toColumn на столбец
     * @return возможность ходить в заданную позицию
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int deltaLine = Math.abs(line - toLine);
        int deltaColumn = Math.abs(column - toColumn);

        if ((deltaLine == 2 && deltaColumn == 1) || (deltaLine == 1 && deltaColumn == 2)) {
            ChessPiece target = chessBoard.board[toLine][toColumn];
            return target == null || !target.getColor().equals(this.color); // Клетка пустая или на ней фигура другого цвета
        }

        return false;
    }


    /**
     * Метод возвращает символ фигуры
     * @return символ фигуры
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
