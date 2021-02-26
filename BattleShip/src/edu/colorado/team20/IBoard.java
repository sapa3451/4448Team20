package edu.colorado.team20;

public interface IBoard {
    void Show();
    boolean CheckSpot(char col, int row);
    void MarkBoard(char col, int row);
    boolean SetShipPos(int row, char col, int direction, int size);
    int getRowSize();
    int getColumnSize();
    char GetPositionChar(char col, int row);
}
