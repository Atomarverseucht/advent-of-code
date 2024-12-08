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
    static boolean isRecursive = false;
    static boolean  count = false;

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
        ex2(input);
        System.out.println("Ergebnis: " + result);

    }
    
    public static void ex1(char[][] input) {
        guardPosition = getGuard(input);
        while (!end && !isRecursive) { 
           input = walk(input, guardPosition);
        }
        result = countCharValue(input, '0');
        result += countCharValue(input, '1');
        result += countCharValue(input, '2');
        result += countCharValue(input, '3');
    }

    public static void ex2(char[][] input) {
        int[] startGuardPosition = getGuard(input);
        
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                int[][] frequency = new int[input.length][input[0].length];
                guardPosition = startGuardPosition.clone();
                if(input[i][j] != '#' && (i != guardPosition[0] || j != guardPosition[1])){
                    end = false;
                    isRecursive = false;
                    char[][] newInput = new char[input.length][];
                    for (int k = 0; k < input.length; k++) {
                        newInput[k] = input[k].clone();
                    }
                    newInput[i][j] = '#';
                    while (!end && !isRecursive) {
                        walk(newInput, guardPosition, frequency);
                    }
                    if(isRecursive){
                        result++;
                    }
                }
            }
        }
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
        input[row][column] = Character.forDigit(direction, 10); 
        
        switch (direction) {
            case 0: if (row != 0) { 
                if(!changeGuardDirection(input, row-1, column)) { 
                    guardPosition[0] = row-1;} 
                    else{input[row][column] = '4';}} else {end = true;} break;
            case 1: if (column != input[0].length-1) {if(!changeGuardDirection(input, row, column+1)) {   guardPosition[1] = column+1;}else if(count == true){isRecursive = true;}else{count = true;}} else {end = true;} break;
            case 2: if (row != input.length-1) { if(!changeGuardDirection(input, row+1, column)) {        guardPosition[0] = row+1;}else if(count == true){isRecursive = true;}else{count = true;}} else {end = true;} break;
            case 3: if (column != 0) {  if(!changeGuardDirection(input, row, column-1)) {                 guardPosition[1] = column-1;}else if(count == true){isRecursive = true;}else{count = true;}} else {end = true;} break;
            default:
        }
        return input;
    }
    
    public static char[][] walk(char[][] input, int[] guardPosition, int[][] frequency){
        int row = guardPosition[0];
        int column = guardPosition[1];
        int direction = guardPosition[2];
        if(Character.isDigit(input[row][column]) ){if(frequency[row][column] >= 5){isRecursive = true; return input;}else{frequency[row][column]++;}}
        input[row][column] = Character.forDigit(direction, 10); 
        
        switch (direction) {
            case 0: if (row != 0) { 
                if(!changeGuardDirection(input, row-1, column)) { 
                    guardPosition[0] = row-1;} 
                    else{input[row][column] = '4';}} else {end = true;} break;
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
            guardPosition[2] %= 4;
            return true;
        }
        return false;
    }
}
