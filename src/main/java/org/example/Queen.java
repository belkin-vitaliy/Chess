package org.example;

/**
 * Ферзь
 */
public class Queen extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Queen(String color) {
        super(color);
    }

    // Метод getColor возвращает цвет фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // Метод canMoveToPosition проверяет, может ли ферзь ходить в заданную позицию
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем валидность позиций
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        // Ферзь не может оставаться на том же месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем, что ход либо диагональный, либо по прямой
        int deltaLine = Math.abs(line - toLine);
        int deltaColumn = Math.abs(column - toColumn);
        if (deltaLine == deltaColumn || line == toLine || column == toColumn) {
            // Проверяем, что путь свободен
            return isPathClear(chessBoard, line, column, toLine, toColumn);
        }

        return false;
    }

    // Метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "Q"; // Символ ферзя
    }

    // Проверяет, что путь свободен
    private boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int stepLine = Integer.compare(toLine, line);
        int stepColumn = Integer.compare(toColumn, column);

        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;

        while (currentLine != toLine || currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // Путь заблокирован
            }
            currentLine += stepLine;
            currentColumn += stepColumn;
        }

        return true;
    }

    // Проверяет, что позиция на доске
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
