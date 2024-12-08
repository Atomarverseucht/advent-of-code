package day8;

public class Position {
    int row; int column;

    public void deltaPosition(Position p1, Position p2){
        this.row = p1.row - p2.row;
        this.column = p1.column - p2.column;
    }
}
