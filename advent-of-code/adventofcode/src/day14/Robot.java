package day14;

import day8.Position;
import java.util.ArrayList;

public class Robot {
    Position position;
    Position velocity;

    Robot(Position position, Position velocity){
        this.position = position;
        this.velocity = velocity;
    }

    public void moveRobot(Position maxPos){
        int row; int column;
        row = position.row + velocity.row;
        column = position.column + velocity.column;

        if(row < 0){ row = maxPos.row +1 - row;}
        if(column < 0){column = maxPos.column +1 - column;}
        if(row > maxPos.row){row -= maxPos.row +1;}
        if(column > maxPos.column){column -= maxPos.column +1;}

        position = Position.getPosition(row, column);
    }

    public static int[] countRobotsInQuadrats(ArrayList<Robot> robots, Position maxPos) {
        int[] out = new int[4];
        Position mid = Position.getPosition(maxPos.row / 2, maxPos.column / 2);

        for (Robot r : robots) {
            Position pos = r.position;
            if(pos.row == mid.row || pos.column == mid.column){ }
            else if(pos.row < mid.row){
                if(pos.column < mid.column){
                    out[0]++;
                } else{
                    out[1]++;
                }
            } else if(pos.column < mid.column){
                out[2]++;
            }else{
                out[3]++;
            }
        }
        return out;
    }
}
