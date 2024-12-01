package org.example;

/**
 * Шахматная доска
 */
public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    /**
     * Метод moveToPosition  ходить в заданную позицию
     *
     * @param startLine
     * @param startColumn
     * @param endLine
     * @param endColumn
     * @return можно ходить в заданную позицию
     */
    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            // Проверка цвета фигуры
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            ChessPiece piece = board[startLine][startColumn];

            // Проверяем, может ли фигура двигаться
            if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = piece; // Перемещаем фигуру
                board[startLine][startColumn] = null; // Очищаем предыдущую клетку
                piece.check = false; // Фигура больше не может участвовать в рокировке
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }

    /**
     * рокировка по 0 столбцу
     *
     * @return выполнена
     */
    public boolean castling0() {
        int line = nowPlayer.equals("White") ? 0 : 7; // Ряд короля для белых и черных
        ChessPiece king = board[line][4];
        ChessPiece rook = board[line][0];

        // Проверяем наличие короля и ладьи
        if (!(king instanceof King) || !(rook instanceof Rook)) return false;

        // Проверяем, двигались ли фигуры
        if (!king.check || !rook.check) return false;

        // Проверяем, что клетки между королем и ладьей пусты
        if (board[line][1] != null || board[line][2] != null || board[line][3] != null) return false;

        // Проверяем, что король не находится под атакой и клетки, через которые он проходит, не атакованы
        King kingPiece = (King) king;
        if (kingPiece.isUnderAttack(this, line, 4) ||
                kingPiece.isUnderAttack(this, line, 3) ||
                kingPiece.isUnderAttack(this, line, 2)) {
            return false;
        }

        // Совершаем рокировку
        board[line][2] = king;
        board[line][4] = null;
        board[line][3] = rook;
        board[line][0] = null;

        // Обновляем статус короля и ладьи
        king.check = false;
        rook.check = false;

        // Меняем ход игрока
        this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

        return true;
    }

    /**
     * рокировка по 7 столбцу
     *
     * @return выполнена
     */
    public boolean castling7() {
        int line = nowPlayer.equals("White") ? 0 : 7; // Ряд короля для белых и черных
        ChessPiece king = board[line][4];
        ChessPiece rook = board[line][7];

        // Проверяем наличие короля и ладьи
        if (!(king instanceof King) || !(rook instanceof Rook)) return false;

        // Проверяем, двигались ли фигуры
        if (!king.check || !rook.check) return false;

        // Проверяем, что клетки между королем и ладьей пусты
        if (board[line][5] != null || board[line][6] != null) return false;

        // Проверяем, что король не находится под атакой и клетки, через которые он проходит, не атакованы
        King kingPiece = (King) king;
        if (kingPiece.isUnderAttack(this, line, 4) ||
                kingPiece.isUnderAttack(this, line, 5) ||
                kingPiece.isUnderAttack(this, line, 6)) {
            return false;
        }

        // Совершаем рокировку
        board[line][6] = king;
        board[line][4] = null;
        board[line][5] = rook;
        board[line][7] = null;

        // Обновляем статус короля и ладьи
        king.check = false;
        rook.check = false;

        // Меняем ход игрока
        this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

        return true;
    }


    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
