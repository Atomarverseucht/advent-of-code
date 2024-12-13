package day12;

import day8.Position;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class Day12{
    public static int result = 0;
    public static List<Position> posLog = new ArrayList<>();
    public static int borders = 1;
    public static List<Error> err = new ArrayList<>();
    public static int[][] log = new int[140][140];

    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        //System.out.println();
        String fileName = "advent-of-code/adventofcode/src/day12/test.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        char[][] field = new char[line.size()][line.get(0).length()];
        for (int i = 0; i < field.length; i++) {
            field[i] = line.get(i).toCharArray();
        }

        ex1(field);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        ex2(field);
        System.out.println("Exercise 2: " + result);

       } catch(Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
       }
    }

    public static void ex1(char[][] field){
        List<Region> regions = new ArrayList<>();
        final Position maxPos = Position.getPosition(field.length - 1, field[0].length - 1);
        
        // Regions einlesen:
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                Position p = Position.getPosition(i, j);
                if(!Position.existPosition(posLog.toArray(new Position[0]), p)){
                    regions.add(Region.getRegion(p, field[i][j]));
                    walk(regions.getLast(), p, field, maxPos);
                    regions.getLast().plots.removeFirst();
                }
            }
        }

        // Wertberechnung
        for (Region region : regions) {
            int scope = 4 * region.plots.size();
            for(Position plot : region.plots) {
                Position[] pos = plot.getNearPositions(maxPos);
                
                for (Position posit : pos) {
                    if(Position.existPosition(region.plots.toArray(new Position[0]), posit)){
                        scope--;
                    }
                }
            }
            result += scope * region.plots.size();
        }
    }

    public static void walk(Region region, Position pos, char[][] field, Position maxPos) {

        if(Position.existPosition(posLog.toArray(new Position[0]), pos)){return;}
        if(region.flower == field[pos.row][pos.column]){
            posLog.add(pos);
            region.plots.add(pos);
            Position[] neighbours = pos.getNearPositions(maxPos);
            for (Position position : neighbours) {
                walk(region, position, field, maxPos);
            }
        }
    }

    public static void ex2(char[][] field){
        posLog.clear();
        List<Region> regions = new ArrayList<>();
        final Position maxPos = Position.getPosition(field.length - 1, field[0].length - 1);
        
        // Regions einlesen:
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                Position p = Position.getPosition(i, j);
                if(!Position.existPosition(posLog.toArray(new Position[0]), p)){
                    regions.add(Region.getRegion(p, field[i][j]));
                    walk(regions.getLast(), p, field, maxPos);
                    regions.getLast().plots.removeFirst();
                }
            }
        }

        
        // Wertberechnung
        for (Region region : regions) {
            borders = 0;
            posLog.clear();
            for (int[] lrow : log) {
                for (int logs : lrow) {
                    logs = 0;
                }
            }
            Position startPos = Position.getStartPosition(region.plots, maxPos);
            log[startPos.row] [startPos.column] =1 ;
            Position[] neighbours = startPos.getNearPos(maxPos);
            Position[] positions = region.plots.toArray(new Position[0]);
            System.out.println(startPos.toString()); 
            if(Position.existPosition(positions, neighbours[1])){
                borders++;
                calculateBorders(positions, startPos, neighbours[1], maxPos, 3);
            } else if(Position.existPosition(positions, neighbours[2])){
                calculateBorders(positions, startPos,neighbours[2], maxPos, 0);
            } else{
                borders++;
                calculateBorders(positions, startPos, startPos, maxPos, 1);
            }

            result += borders * region.plots.size();
        } 
    }

    public static void calculateBorders(Position[] positions, Position startPosition, Position pos, Position maxPos, int direction){
        if(pos.isOutOfBound(maxPos) || !Position.existPosition(positions, pos) || (Position.comparePosition(pos, startPosition) && direction == 0) || direction < 0 || direction > 3) { return;} 
        //if(log[pos.row][pos.column] >= 4){return;} log[pos.row][pos.column]++;
        //System.out.print(pos.toString());
        Position[] neighbours = pos.getNearPos(maxPos);
        neighbours = Position.deleteOutofBounds(neighbours, maxPos);
        int i = 2 * direction +1;
        int i2 = direction == 3?0:i+1;
        if(Position.existPosition(positions, neighbours[i])){
            direction = (direction==0)? 3 : direction - 1;
            borders++;
            calculateBorders(positions, startPosition, neighbours[i], maxPos, direction);
        } else if(Position.existPosition(positions, neighbours[i2])){
            calculateBorders(positions, startPosition, neighbours[i2], maxPos, direction);
        } else{
            borders++;
            direction = (direction==3)? 0 : direction + 1;
            calculateBorders(positions, startPosition, pos, maxPos, direction);
        }
    }
    
}