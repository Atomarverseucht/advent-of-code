package day6;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class Day6 {
    public static int result = 0;
    public static int[] guardPosition = new int[3];
    static  boolean end = false;
    public static void main(String[] args) throws Exception {
        System.out.println("Nikolaus!");

        // Daten aus Datei einlesen
        String fileName = "advent-of-code/adventofcode/src/day6/input.txt";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        char[][] input = new char[allLines.size()][];
        for (int i = 0; i < allLines.size(); i++) {
            input[i] = allLines.get(i).toCharArray();
        }
        ex1(input);
        System.out.println("Ergebnis: " + result);System.out.println(input[0][4]);
    }
    
    public static void ex1(char[][] input) {
        guardPosition = getGuard(input);
        while (!end) { 
           input = walk(input, guardPosition);
        }
        result = countCharValue(input, 'X');
    }

    public static int[] getGuard(char[][] input){ // output[0] = row, out[1] = column, out[2] = direction
        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[row].length; column++){
                int direction = getGuardDirection(input, row, column);
                if(direction != -1){
                    int[] out = new int[3];
                    out[0] = row;
                    out[1] = column;
                    out[2] = direction;
                    return out;
                }
            }
        }
        throw new OutOfMemoryError();
    }

    public static int getGuardDirection(char[][] input, int row, int column){
        switch (input[row][column]) {
            case '^': return 0;
            case '>': return 1;
            case 'v': return 2;
            case '<': return 3;
            default: return -1;
        }
    }

    public static char[][] walk(char[][] input, int[] guardPosition){
        int row = guardPosition[0];
        int column = guardPosition[1];
        int direction = guardPosition[2];
        input[row][column] = 'X'; 
        
        switch (direction) {
            case 0: if (row != 0) { if(!changeGuardDirection(input, row-1, column)) {                     guardPosition[0] = row-1;}} else {end = true;} break;
            case 1: if (column != input[0].length-1) {if(!changeGuardDirection(input, row, column+1)) {   guardPosition[1] = column+1;}} else {end = true;} break;
            case 2: if (row != input.length-1) { if(!changeGuardDirection(input, row+1, column)) {        guardPosition[0] = row+1;}} else {end = true;} break;
            case 3: if (column != 0) {  if(!changeGuardDirection(input, row, column-1)) {                 guardPosition[1] = column-1;}} else {end = true;} break;
            default:
        }
        return input;
    }

    public static int countCharValue(char[][] input, char charValue){
        int count = 0;
        for (char[] row : input) {
            for (char place : row) {
                if(place == charValue){
                    count++;
                }
            }
        }
        return  count;
    }

    public static boolean changeGuardDirection(char[][] input, int x, int y){
        if(input[x][y] == '#'){
            guardPosition[2]++;
            if(guardPosition[2] > 3) {
                guardPosition[2] = 0;
            } return true;
        }
        return false;
    }
}
