package day11;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {
    public static long result = 0;
    public static int count = 0; 
    public static final int blinkCount = 6;
    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        String fileName = "advent-of-code/adventofcode/src/day11/test.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        String[] help = line.get(0).split(" ");
        int[] nums = new int[help.length];
        for (int j = 0; j < help.length; j++) {
            nums[j] = Integer.parseInt(help[j]);
        }
        

        //Aufgaben ausführen
        ex1(nums);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        //ex2(map);
        System.out.println("Exercise 2: " + result);

       } catch(Exception e){
            System.out.println("err");
            e.printStackTrace();
       }
    }

    public static void ex1(int[] nums){
        List<Long> numbers = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            numbers.add(Integer.toUnsignedLong(nums[i]));
        }

        for (int i = 0; i < blinkCount; i++) {
            for (int j = 0; j < 2 * numbers.size(); j++) {
                long stone = numbers.get(j);

                if(stone == Long.valueOf(0)){
                    numbers.set(j, Long.valueOf(1));
                }else if((int)(stone /10) % 2 == 1){
                    char[] num = Long.toString(stone).toCharArray();
                    int length = num.length;
                    String stone1 = "";
                    String stone2 = "";
                    for (int k = 0; k < length; k++) {
                        char point = num[k];
                        if(k < length / 2){
                            stone1 += point;
                        } else{
                            stone2 += point;
                        }
                    }
                    numbers.set(j, Long.parseLong(stone2));
                    numbers.add(j, Long.parseLong(stone1));
                    j++;
                } else {
                    numbers.set(j, numbers.get(j)*2024);
                }
                if(j >= numbers.size()-1){
                    break;
                }
            }
            System.out.println(numbers);
        }
    }
}
