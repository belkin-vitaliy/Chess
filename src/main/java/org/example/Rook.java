package org.example;

/**
 * ладья
 */
public class Rook extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Rook(String color) {
        super(color);
    }

    // Метод getColor возвращает цвет фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // Метод canMoveToPosition проверяет, может ли ладья ходить в заданную позицию
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся на доске
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        // Ладья не может оставаться на том же месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Ладья может ходить только по прямой линии
        if (line != toLine && column != toColumn) {
            return false;
        }

        // Проверяем, что путь между начальной и конечной точками свободен
        if (line == toLine) { // Горизонтальный ход
            int step = (toColumn - column) > 0 ? 1 : -1;
            for (int currentColumn = column + step; currentColumn != toColumn; currentColumn += step) {
                if (chessBoard.board[line][currentColumn] != null) {
                    return false; // Путь заблокирован
                }
            }
        } else { // Вертикальный ход
            int step = (toLine - line) > 0 ? 1 : -1;
            for (int currentLine = line + step; currentLine != toLine; currentLine += step) {
                if (chessBoard.board[currentLine][column] != null) {
                    return false; // Путь заблокирован
                }
            }
        }

        return true; // Все проверки пройдены, ход возможен
    }

    // Метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "R"; // Символ ладьи
    }

    // Вспомогательный метод для проверки, что позиция находится на доске
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
