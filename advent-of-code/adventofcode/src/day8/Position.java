package day8;

import java.util.ArrayList;

// important help class for Day 8, 10, 12 and 14
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

    @Override
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

    public Position[] getNearPos(Position maxPos){
        Position[] out = new Position[8];

        if(column != 0){
            out[0] = getPosition(row, column - 1);
            if(row != maxPos.row){
                out[1] = getPosition(row+1, column - 1);
            }
        }
        if(row != maxPos.row){
            out[2] = getPosition(row+1, column);
            if(column != maxPos.column){
                out[3] = getPosition(row + 1, column + 1);
            }
        }
        if(column != maxPos.column){
            out[4] = getPosition(row, column + 1);
            if(row != 0){
                out[5] = getPosition(row-1, column+1);
            }
        }
        if(row != 0){
            out[6] = getPosition(row-1, column);
            if(column != 0){
                out[7] = getPosition(row-1, column-1);
            }
        }
        return out;
    }

    public static boolean existPosition(Position[] positions, Position value){
        if(value == null){ return false;}
        for (Position position : positions) {
            if(value.row == position.row && value.column == position.column){
                return true;
            }
        }
        return false;
    }

    public static Position getStartPosition(ArrayList<Position> positions, Position maxPosition){
        Position out = Position.getPosition(maxPosition.row, maxPosition.column);
        for (Position pos : positions) {
            if(pos.row < out.row || (pos.row == out.row && pos.column < out.column)){
                out = pos;
            }
        }
        return out;
    }

    public static boolean comparePosition(Position p1, Position p2){
        if(p1.row == p2.row && p1.column == p2.column){
            return true;
        } return false;
    }

    public static Position[] deleteOutofBounds(Position[] pos, Position maxP){
        for (Position p : pos) {
            if(p != null && (p.row < 0 || p.row > maxP.row || p.column < 0 || p.column > maxP.column)){
                p = null;
            }
        }
        return pos;
    }

    public boolean isOutOfBound(Position maxP){
        Position p = this;
        if(p == null || p.row < 0 || p.row > maxP.row || p.column < 0 || p.column > maxP.column){
            return true;
        }
        return false;
    }
}
