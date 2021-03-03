package edu.colorado.team20;

public class HiddenBoardShow implements ShowBehavior{
    public void show (Board board) {
        // create arrays to hold board rows and columns
        char[] col = new char[board.getColumnSize()*3+2];
        int [] row = new int[board.getRowSize()];
        char start = 'A';
        col[0] = ' ';
        for (int i = 1; i < board.getColumnSize()*3-1; i = i + 3) {
            col[i+1] = ' ';
            col[i+2] = ' ';
            col[i+3] = start;
            start += 1;
        }
        for (int i = 0; i < board.getRowSize(); i++) {
            row[i] = i+1;
        }

        // print board
        for (int i = 0; i < board.getRowSize(); i++) {
            if (i == 0) {
                System.out.println(col);
            }

            // this conditional fixes number lining
            if (row[i] >= 10) {
                System.out.print(row[i]);
            } else {
                System.out.print(row[i] + " ");
            }
            for (int j = 0; j < board.columnSize; j++) {
                if (board.getBoard()[i][j] == 'D') {
                    System.out.print("[D]");
                }
                else if (board.getBoard()[i][j] == 'X') {
                    System.out.print("[X]");
                }
                else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
