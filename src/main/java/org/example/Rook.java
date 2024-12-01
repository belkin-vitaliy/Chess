package org.example;

/**
 * ладья
 */
public class Rook extends ChessPiece {

    /**
     * Конструктор принимает цвет фигуры
     *
     * @param color цвет фигуры
     */
    public Rook(String color) {
        super(color);
    }

    /**
     * Метод getColor возвращает цвет фигуры
     *
     * @return цвет фигуры
     */
    @Override
    public String getColor() {
        return this.color;
    }

    /**
     * Метод canMoveToPosition проверяет, может ли ладья ходить в заданную позицию
     *
     * @param chessBoard Шахматная доска
     * @param line       строка
     * @param column     столбец
     * @param toLine     на строка
     * @param toColumn   на столбец
     * @return может ли ладья ходить в заданную позицию
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        if (line != toLine && column != toColumn) {
            return false; // Не прямой ход
        }

        int stepLine = (toLine - line) == 0 ? 0 : (toLine - line) > 0 ? 1 : -1;
        int stepColumn = (toColumn - column) == 0 ? 0 : (toColumn - column) > 0 ? 1 : -1;

        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;

        while (currentLine != toLine || currentColumn != toColumn) {
            if (currentLine == toLine && currentColumn == toColumn) {
                break;
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
     *
     * @return символ фигуры
     */
    @Override
    public String getSymbol() {
        return "R"; // Символ ладьи
    }

    /**
     * Вспомогательный метод для проверки, что позиция находится на доске
     *
     * @param line   строка
     * @param column столбец
     * @return роверки, что позиция находится на доске
     */
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
