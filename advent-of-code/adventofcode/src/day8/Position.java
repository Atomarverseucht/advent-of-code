package day8;

public class Position {
    int row; int column;

    public void deltaPosition(Position p1, Position p2){
        this.row = p1.row - p2.row;
        this.column = p1.column - p2.column;
    }

    public static Position getPosition(int row, int column){
        Position p = new Position();
        p.column = column;
        p.row = row;
        return p;
    }

    public String toString(){
        return Integer.toString(row) + "|" + Integer.toString(column);
    }
}
