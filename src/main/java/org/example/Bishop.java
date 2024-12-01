package org.example;

/**
 * Слон
 */
public class Bishop extends ChessPiece {

    /**
     * Конструктор принимает цвет фигуры
     * @param color цвет фигуры
     */
    public Bishop(String color) {
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
     * Метод canMoveToPosition проверяет, может ли слон ходить в заданную позицию
     * @param chessBoard Шахматная доска
     * @param line строка
     * @param column столбец
     * @param toLine на строку
     * @param toColumn на столбец
     * @return может ли слон ходить в заданную позицию
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
        if (deltaLine != deltaColumn) {
            return false; // Не диагональный ход
        }

        int stepLine = (toLine - line) > 0 ? 1 : -1;
        int stepColumn = (toColumn - column) > 0 ? 1 : -1;

        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;

        while (currentLine != toLine || currentColumn != toColumn) {
            if (currentLine == toLine && currentColumn == toColumn) {
                break; // Конечная клетка может быть занята фигурой другого цвета
            }
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // Путь заблокирован
            }
            currentLine += stepLine;
            currentColumn += stepColumn;
        }

        ChessPiece target = chessBoard.board[toLine][toColumn];
        return target == null || !target.getColor().equals(this.color);
    }


    /**
     * Метод возвращает символ фигуры
     * @return символ фигуры
     */
    @Override
    public String getSymbol() {
        return "B"; // Символ слона
    }

    /**
     * Вспомогательный метод для проверки, что позиция находится на доске
     * @param line строка
     * @param column столбец
     * @return позиция находится на доске
     */
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
