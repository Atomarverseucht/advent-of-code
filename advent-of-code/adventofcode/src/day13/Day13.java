package day13;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day13 {

    public static long result = 0;
    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        long startTime = System.currentTimeMillis();
        String fileName = "advent-of-code/adventofcode/src/day13/input.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        int[][] field = new int[(line.size()/4)+1][6];
        for (int i = 0; i < line.size(); i++) {
            if(i%4 == 3){continue;} 
            else{
                String row = line.get(i);
                row = row.replace("Button ", "").replace("Prize: ","").replace("A: ", "").replace("B: ", "").replace("X", "").replace("Y", "").replace("+", "").replace("=", "");
                String[] col = row.split(", ");
                int f = 2*(i % 4);
                for (int j = 0; j < 2; j++) {
                    field[i/4][f+j] = Integer.parseInt(col[j]);
                }
            }
        }

        ex1(field);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        ex2(field);
        System.out.println("Exercise 2: " + result);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Runtime: " + totalTime +"ms");

       } catch(Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
       }
    }

    public static void ex1(int[][] values){
        for (int i = 0; i < values.length; i++) {
            int[] val = values[i];
            int a2 = (val[1]*val[4] - val[5]*val[0]) / (val[1]*val[2] - val[3]*val[0]);
            int a1 = (val[5] - a2*val[3]) / val[1];
            if((a1*val[0]+a2*val[2]) == val[4] && (a1*val[1] + a2*val[3]) == val[5]){
                result += 3*a1 + 1*a2;
            }
        }
    }
    
    public static void ex2(int[][] values){
        for (int i = 0; i < values.length; i++) {
            long[] val = Arrays.stream(values[i]).asLongStream().toArray();
            long v = 10000000000000L;
            val[4] += v;
            val[5] += v;
            long a2 = (val[1]*val[4] - val[5]*val[0]) / (val[1]*val[2] - val[3]*val[0]);
            long a1 = (val[5] - a2*val[3]) / val[1];
            if((a1*val[0]+a2*val[2]) == val[4] && (a1*val[1] + a2*val[3]) == val[5]){
                result += 3*a1 + 1*a2;
            }
        }
    }
}
