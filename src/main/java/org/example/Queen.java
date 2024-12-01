package org.example;

/**
 * Ферзь
 */
public class Queen extends ChessPiece {

    /**
     * Конструктор принимает цвет фигуры
     *
     * @param color цвет фигуры
     */
    public Queen(String color) {
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
     * Метод canMoveToPosition проверяет, может ли ферзь ходить в заданную позицию
     *
     * @param chessBoard Шахматная доска
     * @param line       строка
     * @param column     столбец
     * @param toLine     на строка
     * @param toColumn   на столбец
     * @return может ли ферзь ходить в заданную позицию
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

        if (deltaLine == deltaColumn || line == toLine || column == toColumn) {
            return isPathClear(chessBoard, line, column, toLine, toColumn);
        }

        return false;
    }

    /**
     * Проверяет, что путь свободен
     *
     * @param chessBoard шахматная доска
     * @param line       строка
     * @param column     столбец
     * @param toLine     на строку
     * @param toColumn   на столбец
     * @return путь свободен
     */
    private boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int stepLine = Integer.compare(toLine, line);
        int stepColumn = Integer.compare(toColumn, column);

        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;

        while (currentLine != toLine || currentColumn != toColumn) {
            if (currentLine == toLine && currentColumn == toColumn) {
                break;
            }
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
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
        return "Q"; // Символ ферзя
    }

    /**
     * Проверяет, что позиция на доске
     *
     * @param line   строка
     * @param column столбец
     * @return позиция на доске
     */
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
