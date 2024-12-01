package org.example;

public class Bishop extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Bishop(String color) {
        super(color);
    }

    // Метод getColor возвращает цвет фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // Метод canMoveToPosition проверяет, может ли слон ходить в заданную позицию
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся на доске
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        // Слон не может оставаться на том же месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем, что ход диагональный
        int deltaLine = Math.abs(line - toLine);
        int deltaColumn = Math.abs(column - toColumn);
        if (deltaLine != deltaColumn) {
            return false; // Не диагональный ход
        }

        // Проверяем, что путь между начальной и конечной точками свободен
        int stepLine = (toLine - line) > 0 ? 1 : -1; // Направление движения по строкам
        int stepColumn = (toColumn - column) > 0 ? 1 : -1; // Направление движения по столбцам

        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;
        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // Путь заблокирован
            }
            currentLine += stepLine;
            currentColumn += stepColumn;
        }

        return true; // Все проверки пройдены, ход возможен
    }

    // Метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "B"; // Символ слона
    }

    // Вспомогательный метод для проверки, что позиция находится на доске
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
