package org.example;

/**
 * Король
 */
public class King extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public King(String color) {
        super(color);
    }

    // Метод getColor возвращает цвет фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // Метод canMoveToPosition проверяет, может ли король ходить в заданную позицию
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

        if (deltaLine <= 1 && deltaColumn <= 1) {
            ChessPiece target = chessBoard.board[toLine][toColumn];
            return target == null || !target.getColor().equals(this.color);
        }

        return false;
    }


    // Метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "K"; // Символ короля
    }

    // Проверяет, находится ли клетка под атакой
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        // Перебираем все клетки на доске
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                // Если клетка занята фигурой противоположного цвета и она может атаковать
                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true; // Поле под атакой
                    }
                }
            }
        }
        return false; // Поле не под атакой
    }

    // Проверяет, что позиция на доске
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
