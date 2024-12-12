package day12;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import day8.Position;

public final class Day12{
    public static int result = 0;
    public static List<Position> posLog = new ArrayList<>();

    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        //System.out.println();
        String fileName = "advent-of-code/adventofcode/src/day12/input.txt";
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
            System.out.println();
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
            } System.out.println(regions.toString());
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
            int scope = 0;
            posLog.clear();
            Position startPos = Position.getStartPosition(region.plots, maxPos);
            
            result += scope * region.plots.size();
        } 
    }

    public int calculateBorders(Position[] positions, Position startPosition, Position pos, Position maxPos, Region reg, String direction){
        if(Position.existPosition(positions, maxPos)){ } return -1;
    }
}