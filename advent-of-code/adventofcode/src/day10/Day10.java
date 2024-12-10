package day10;

import day8.Position;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class Day10 {
    public static long result = 0;
    public static int count = 0; 
    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        //System.out.println();
        String fileName = "advent-of-code/adventofcode/src/day10/input.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        int[][] map = new int[line.size()][line.get(0).length()];
        for (int i = 0; i < line.size(); i++) {
            char[] helpChar = line.get(i).toCharArray();
            for (int j = 0; j < helpChar.length; j++) {
                map[i][j] = Character.getNumericValue(helpChar[j]);
            }
        }
        ex1(map);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        ex2(map);
        System.out.println("Exercise 2: " + result);

       } catch(Exception e){
            System.out.println("err");
            e.printStackTrace();
       }
    }

    public static void ex1(int[][] map){
        List<Position> groundPos = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            int mapRow[] = map[i];
            for (int j = 0; j < mapRow.length; j++) {
                int mapPos = mapRow[j];
                if(mapPos == 0){
                    groundPos.add(Position.getPosition(i, j));
                }
            }
        }

       
        for (Position gPos : groundPos) {
            List<Position> reachedPeak = new ArrayList<>();
            if(gPos != null){
                List<Position> help = walkToPeak(map, gPos);
                for (Position position : help) {
                    if(getValueOfPosition(map, position) == 9 && !Position.existPosition(reachedPeak.toArray(new Position[0]), position)){
                        reachedPeak.add(position);
                    }
                }
            }   
            result += reachedPeak.size(); 
        }
        
    }
    

    public static void ex2(int[][] map){

        List<Position> groundPos = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            int mapRow[] = map[i];
            for (int j = 0; j < mapRow.length; j++) {
                int mapPos = mapRow[j];
                if(mapPos == 0){
                    groundPos.add(Position.getPosition(i, j));
                }
            }
        }

        for (Position gPos : groundPos) {
            List<Position> reachedPeak = new ArrayList<>();
            count = 0;
            if(gPos != null){
                List<Position> help = walkToPeak(map, gPos);
                for (Position position : help) {
                    if(getValueOfPosition(map, position) == 9){
                        count++;
                    }
                }
            }   
            result += count; 
        }
    }


    public static List<Position> walkToPeak(int[][] map, Position p){
        Position maxPos = Position.getPosition(map.length-1, map[0].length-1);  
        List<Position> out = new ArrayList<>();
        
        Position next[] = p.getNearPositions(maxPos);
        next = getPassablePositions(map, next, p);

        if(getValueOfPosition(map, p) == 9){ out.add(p); return out;}
        for (Position position : next) {
            if(position != null){
                List<Position> posit = walkToPeak(map, position);
            for (Position position2 : posit) {
                out.add(position2);
            }
            }
            
        }
        return out;
    }
    public static Position[] getPassablePositions(int[][] map, Position[] nearPosition, Position pos){
        int p = getValueOfPosition(map, pos);
        List<Position> out = new ArrayList<>();
        for (Position position : nearPosition) {
            int p2 = getValueOfPosition(map, position);
            if(p2 - p == 1){
                out.add(position);
            }   
        }
        return out.toArray(new Position[0]);
    }

    public static int getValueOfPosition(int[][] map, Position pos){
        return map[pos.row][pos.column];
    }

}
