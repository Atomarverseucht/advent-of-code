package day9;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day9 {
    public static int result = 0;
    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        //System.out.println();
        String fileName = "advent-of-code/adventofcode/src/day8/input.txt";
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
        System.out.println("Exercise 2: "+result);

       } catch(Exception e){
            System.out.println();
            e.printStackTrace();
       }
    }
    public static void ex1(char[][] field){

    }
    public static void ex2(char[][] field){

    }
}
