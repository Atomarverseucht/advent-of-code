package day16;

import day8.Position;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day16{
    public static long result = 0;
    public static long[][][] log;
    public static void main(String[] args) throws Exception{
        //Daten Einlesen aus txt
        long startTime = System.currentTimeMillis();
        String fileName = "advent-of-code/adventofcode/src/day16/test.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        char[][] field = new char[line.size()][line.get(0).length()];
        for (int i = 0; i < line.size(); i++) {
            field[i] = line.get(i).toCharArray();
        }
        log = new long[field.length][field[0].length][4];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int j2 = 0; j2 < 4; j2++) {
                    log[i][j][j2] = Long.MAX_VALUE;
                }
            }
        }

        ex1(field);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        test();
        System.out.println("Exercise 2: " + result);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Runtime: " + totalTime +"ms");
    }

    public static void ex1(char[][] field){
        Position start = new Position();
        startSearch:
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if(field[i][j] == 'S'){
                    start = Position.getPosition(i, j);
                    break startSearch;
                }
            }
        }
        System.out.println(start.toString());
        result = walk(field, start.row, start.column, 0, 0);
    }

    public static long walk(char[][] field, int row, int column, int direction, long value){
     System.out.println(direction + "/ "+ value);
        if(log[row][column][direction] <= value || field[row][column] == '#'){System.out.print("h "); return 0;}
        log[row][column][direction] = value;
        if(field[row][column] == 'E'){System.out.print("e "); return value;}

        int[] newPos = newPos(row, column, direction);
        long val1 = walk(field, newPos[0], newPos[1], direction, value+1);
        
        long val2 = walk(field, row, column, (direction == 3)?0:direction+1, value+1000);
       
        long val3 = walk(field, row, column, (direction == 0)?3:direction-1, value+1000);
    
        return val1 + val2 + val3;
    }

    public static int[] newPos(int row, int column, int direction){
        switch(direction){
            case 0: column++;
            case 1: row--;
            case 2: column--;
            case 3: row++;
        }
        int[] out = {row, column};
        return out;
    }

}
