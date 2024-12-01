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
        // Проверяем, что начальная и конечная позиции находятся на доске
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        // Пешка не может оставаться на том же месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Пешка может ходить только вперед
        int direction = this.color.equals("White") ? 1 : -1; // Направление хода
        int startLine = this.color.equals("White") ? 1 : 6; // Начальная линия для первого хода

        // Проверяем движение на 1 клетку вперед
        if (toColumn == column && toLine == line + direction) {
            return chessBoard.board[toLine][toColumn] == null; // Клетка должна быть пустой
        }

        // Проверяем первый ход на 2 клетки вперед
        if (line == startLine && toColumn == column && toLine == line + 2 * direction) {
            return chessBoard.board[line + direction][column] == null && chessBoard.board[toLine][toColumn] == null;
        }

        // Пешка не может ходить в других направлениях
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
