package day1;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class exercise1 {
    static int[] array1 = new int[1000];
    static int[] array2 = new int[1000];
    static int result = 0;
    public static void main(String[] args) throws Exception {
        

        //Daten Einlesen aus txt
        System.out.println("Dec. 1st!");
        String fileName = "D:/,private Projekte/Code/advent-of-code/adventofcode/src/day1/inputEx1.txt";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (int i = 0; i < 1000; i++) {
            String[] value = allLines.get(i).split("   ");
            array1[i] = Integer.parseInt(value[0]);
            array2[i] = Integer.parseInt(value[1]);
        }

        ex2();
        System.out.println(result);
    }

    public static void ex1(){

        Arrays.sort(array1);
        Arrays.sort(array2);

        for (int i = 0; i < 1000; i++) {
            result += Math.abs(array1[i]-array2[i]);
        }
    }

    public static void ex2() {
        int[] häufigkeit = new int[99828];
        for (int i = 0; i < häufigkeit.length; i++) {
            häufigkeit[i] = 0;
        }
        for (int value : array2) {
            häufigkeit[value]++; 
        }
        for (int val : array1) {
            result += val * häufigkeit[val];
        }
    }
}
