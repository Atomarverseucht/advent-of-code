package day15;

import day8.Position;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day15 {
    public static long result = 0;
    public static final int split = 51 - 1;
    public static Position robot;
    public static ArrayList<Position> log = new ArrayList<>();

    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        long startTime = System.currentTimeMillis();
        String fileName = "advent-of-code/adventofcode/src/day15/input.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        char[][] field = getCharArray(line, 0, split);
        System.out.println();
        char[][] commands = getCharArray(line, split+1, line.size());

        ex1(field, commands);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        ex2(newInput(field), commands);
        System.out.println("Exercise 2: " + result);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Runtime: " + totalTime +"ms");
  
       } catch(Exception e){
            //System.out.println(e.toString());
            e.printStackTrace();
       }
    }

    public static void ex1(char[][] field, char[][] commands){
        robotSearch:
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if(field[i][j] == '@'){
                    robot = Position.getPosition(i, j);
                    break robotSearch;
                }
            }
        }

        for (char[] cs : commands) {
            for (char cmd : cs) {
                moveRobot(field, cmd);
            }
        }

        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                if(field[r][c] == 'O'){
                    result += r*100 + c;
                }
            }
            
        }
    }

    public static void ex2(char[][] field, char[][] commands){
        for (char[] cs : commands) {
            for (char cmd : cs) {
                moveRobot(field, cmd);
                log.clear();;
            }
        }

        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                if(field[r][c] == '['){
                    result += r*100 + c;
                }
            }
            
        }
    }

    public static char[][] getCharArray(List<String> input, int begin, int end){
        char[][] out = new char[end - begin][input.get(begin).length()];
        for (int i = begin; i < end; i++) {
            out[i - begin] = input.get(i).toCharArray();
        }
        return out;
    }

    public static char[][] moveRobot(char[][] field, char command){
        int row = robot.row;
        int column = robot.column;
        int direction = 0;
        boolean f = false;
        switch(command){
            case '^': direction = 0; break;
            case 'v': direction = 1; break;
            case '<': direction = 2; break;
            case '>': direction = 3; break;
        }
        f = check(row, column, field, direction); 
        if (f) {
            int[] newPos = newPos(row, column, direction);
            robot.row = newPos[0];
            robot.column = newPos[1];
        }
        return field;
    }

    public static boolean check(int row, int column, char[][] field, int direction){
        if(field[row][column] == '#'){ return false;}
        if(field[row][column] == '.'){
            return true;
        } 
        int[] newPos = newPos(row, column, direction);
        if(field[row][column] == 'O' || field[row][column] == '@'){
            boolean res = check(newPos[0], newPos[1], field, direction);
            if(res == false){
                return false;
            } else{
                field[newPos[0]][newPos[1]] = field[row][column];
                field[row][column] = '.';
                return true;
            } 
        }
        if(Position.existPosition(log, Position.getPosition(row, column))){ return true;}
        log.add(Position.getPosition(row, column));

        // Wenn input [ oder ]
        if(field[row][column] == ']'){ newPos[1]--;}
        boolean pos1 = check(newPos[0], newPos[1], field, direction);
        boolean pos2 = check(newPos[0], newPos[1]+1, field, direction);

        if(pos1 && pos2){
            field[newPos[0]][newPos[1]] = field[row][column];
            field[row][column] = '.';
            return true;
        } else{ return false;}
    }

    public static int[] newPos(int row, int column, int direction){
        int r = 2 * (direction % 2) - 1;
        if(direction /2 == 0){
            row += r;
        } else{
            column += r;
        }
        int[] out = {row, column};
        return out;
    }

    public static char[][] newInput(char[][] field){
        char[][] out = new char[field.length][field[0].length * 2];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                char f = field[i][j];
                char out1, out2;
                if(f == '.' || f == '#'){
                    out1 = f;
                    out2 = f;
                } else if(f == 'O'){
                    out1 = '[';
                    out2 = ']';
                } else{
                    out1 = '@';
                    out2 = '.';
                }
                out[i][2*j] = out1;
                out[i][2*j+1] = out2;
            }
        }
        robot.column *= 2;
        return out;
    }
}
