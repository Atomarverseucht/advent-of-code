package day8;

import java.util.ArrayList;

public final class Position {
    public int row; public int column;

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

    public Position[] getNearPositions(Position maxPos){
        ArrayList<Position> out = new ArrayList<>();
        if(row != 0){out.add(getPosition(row-1, column));}
        if(column != 0){out.add(getPosition(row, column-1));}
        if(row != maxPos.row){out.add(getPosition(row+1, column));}
        if(column != maxPos.column){out.add(getPosition(row, column+1));}
        return out.toArray(new Position[0]);
    }

    public static boolean existPosition(Position[] positions, Position value){
        for (Position position : positions) {
            if(value.row == position.row && value.column == position.column){
                return true;
            }
        }
        return false;
    }
}
