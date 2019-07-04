package com.example.minesweeper;

enum Cell {
    empty,
    one,
    two,
    three,
    four,
    five,
    six,
    seven,
    eight,
    bomb
}

public class CellItem {
    Cell cell;
    int row;
    int col;

    CellItem (Cell cell, int row, int col) {
        this.cell = cell;
        this.row = row;
        this.col = col;
    }
}
