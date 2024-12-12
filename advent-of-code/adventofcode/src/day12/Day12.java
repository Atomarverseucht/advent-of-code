package day12;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import day8.Position;

public final class Day12{
    public static int result = 0;
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
        //ex2(field);
        System.out.println("Exercise 2: "+result);

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
                int index = searchRegion(regions, field[i][j], p, maxPos);
                if(index != -1){
                    Region reg = regions.get(index);
                    reg.plots.add(p);
                    regions.set(index, reg);
                } else{
                    regions.add(Region.getRegion(p, field[i][j]));
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
            System.out.println(region.flower + ": "+region.toString());
            //System.out.println(scope + "*" + region.plots.size() + "=" + scope * region.plots.size());
            result += scope * region.plots.size();
        }
    }

    public static int searchRegion(List<Region> regions, char flower, Position p, Position maxPos){
        for (int i = 0; i < regions.size(); i++) {
            Region reg = regions.get(i);
            if(reg.flower == flower && isInRange(reg.plots, p, maxPos)){
                return i;
            }
        } 
        return -1;
    }

    public static boolean isInRange(List<Position> positions, Position p, Position maxPos){
        Position[] pos = p.getNearPositions(maxPos);
        for (Position posit : pos) {
            if(Position.existPosition(positions.toArray(new Position[0]), posit)){
                return true;
            }
        }
        return false;
    }
}