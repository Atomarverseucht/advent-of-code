package day10;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import day8.Position;

public final class Day10 {
    public static long result = 0;
    public static List<Position> reachedPosition = new ArrayList<>(); 
    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        //System.out.println();
        String fileName = "advent-of-code/adventofcode/src/day10/test.txt";
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
        ex2();
        System.out.println("Exercise 2: " + result);

       } catch(Exception e){
            System.out.println("err");
            e.printStackTrace();
       }
    }

    public static void ex1(int[][] map){
        Position maxPos = Position.getPosition(map.length-1, map[0].length-1);
        List<Position> peakPos = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            int mapRow[] = map[i];
            for (int j = 0; j < mapRow.length; j++) {
                int mapPos = mapRow[j];
                if(mapPos == 9){
                    peakPos.add(Position.getPosition(i, j));
                }
            }
        }

        for (int i = 0; i < peakPos.size(); i++) {
            Position peak = peakPos.get(i);
            if(peak != null){
                List<Position> reachedPeak = new ArrayList<>();
            List<Position> reachPoints = walkToGround(map, peak);
            for (int h = 0; h < reachPoints.size(); ) {
                Position position = reachPoints.get(h);
                if(getValueOfPosition(map, position) != 0){
                    reachPoints.remove(h);
                }
            } 
                for (Position gPos : reachPoints) {
                    if(gPos != null){
                        List<Position> help = walkToPeak(map, gPos);
                        for (Position position : help) {
                            if(getValueOfPosition(map, position) == 9 && !Position.existPosition(reachedPeak.toArray(new Position[0]), position)){
                                reachedPeak.add(position);
                            }
                        }
                    }    
                }
            result += reachedPeak.size();
            } 
        }
    }

    public static void ex2(){
        
    }

    public static List<Position> walkToGround(int[][] map, Position p){
        
        Position maxPos = Position.getPosition(map.length -1, map[0].length-1);  
        List<Position> out = new ArrayList<>();
        Position next[] = p.getNearPositions(maxPos);
        if(Position.existPosition(reachedPosition.toArray(new Position[0]), p)){
            out.add(p);
            return out; 
        }
        if(getValueOfPosition(map, p) == 0){ out.add(p); return out;}
        reachedPosition.add(p);
        if(next != null){
           next = getPassablePositions(map, next, p); 
        }
        
        for (Position position : next) {
            if(position != null){
                List<Position> posit = walkToGround(map, position);
                for (Position position2 : posit) {
                    if(position2 != null){
                        out.add(position2);
                    } 
                }
            }
            
        }
        return out;
    }

    public static List<Position> walkToPeak(int[][] map, Position p){
        Position maxPos = Position.getPosition(map.length, map[0].length);  
        List<Position> out = new ArrayList<>();
        Position next[] = p.getNearPositions(maxPos);
        next = getPassablePositions(map, next, p);

        if(getValueOfPosition(map, p) == 9){ out.add(p); return out;}
        for (Position position : next) {
            List<Position> posit = walkToPeak(map, position);
            for (Position position2 : posit) {
                out.add(position2);
            }
        }
        return out;
    }
    public static Position[] getPassablePositions(int[][] map, Position[] nearPosition, Position pos){
        int p = getValueOfPosition(map, pos);
        List<Position> out = new ArrayList<>();
        for (Position position : nearPosition) {
            if(position != null){
               int p2 = getValueOfPosition(map, position);
            if(Math.abs(p - p2) == 1){
                out.add(position);
            } 
            }
            
        }
        return out.toArray(new Position[0]);
    }

    public static int getValueOfPosition(int[][] map, Position pos){
        return map[pos.row][pos.column];
    }

}