package org.example;

/**
 * Пешка
 */
public class Pawn extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Pawn(String color) {
        super(color);
    }

    // Метод getColor возвращает цвет фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // Метод canMoveToPosition проверяет, может ли пешка ходить в заданную позицию
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = this.color.equals("White") ? 1 : -1;
        int startLine = this.color.equals("White") ? 1 : 6;

        if (toColumn == column) {
            // Ход вперед на 1 клетку
            if (toLine == line + direction && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }

            // Первый ход на 2 клетки вперед
            if (line == startLine && toLine == line + 2 * direction && chessBoard.board[line + direction][column] == null && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
        }

        // Съедание фигуры по диагонали
        if (Math.abs(toColumn - column) == 1 && toLine == line + direction) {
            ChessPiece target = chessBoard.board[toLine][toColumn];
            return target != null && !target.getColor().equals(this.color);
        }

        return false;
    }


    // Метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "P"; // Символ пешки
    }

    // Вспомогательный метод для проверки, что позиция находится на доске
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
